package com.tangem.tangem_sdk_jvm

import com.tangem.CardReader
import com.tangem.Log
import com.tangem.TagType
import com.tangem.TangemSdkError
import com.tangem.common.CompletionResult
import com.tangem.common.apdu.CommandApdu
import com.tangem.common.apdu.ResponseApdu
import com.tangem.common.extensions.hexToBytes
import com.tangem.common.extensions.toHexString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import javax.smartcardio.*

class SmartCardReader(private var terminal: CardTerminal?) : CardReader {
    private var card: Card? = null
    private var channel: CardChannel? = null
    var logException = false

    override var scope: CoroutineScope? = null
    override val tag = ConflatedBroadcastChannel<TagType?>()

    private var connectedTag: TagType? = null
        set(value) {
            field = value
            scope?.launch { tag.send(value) }
        }

    override fun stopSession(cancelled: Boolean) {
        if (card != null) {
            try {
                channel = null
                try {
                    card!!.disconnect(false)
                } catch (e: CardException) {
                    e.message?.let { Log.nfc { it } }
                    if (logException) e.printStackTrace()
                }
                card = null
            } catch (e: CardException) {
                throw IOException(e)
            }
        } else if (terminal?.isCardPresent != false) {
            card = terminal!!.connect("*")
            card?.disconnect(false)
        }
        terminal = null
        card = null
        channel = null
    }

    fun isCardPresent(): Boolean {
        return terminal?.isCardPresent ?: false
    }


    @Throws(CardException::class)
    fun getUID(): ByteArray? {
        val rsp = channel!!.transmit(CommandAPDU("FFCA000000".hexToBytes()))
        return rsp.data
    }

    @Throws(CardException::class)
    override fun startSession() {
        val terminal = terminal ?: throw CardException("No terminal specified!")

        if (terminal.waitForCardPresent(30000)) {
            card = terminal.connect("*")
            channel = card?.basicChannel
            getUID()?.let { Log.nfc { "UID: " + it.toHexString() } }
            connectedTag = TagType.Nfc
        } else {
            throw CardException("Timeout waiting card present!")
        }
    }

    override fun pauseSession() {
    }

    override fun readSlixTag(callback: (result: CompletionResult<ResponseApdu>) -> Unit) {
    }

    override fun resumeSession() {
    }

    override suspend fun transceiveApdu(apdu: CommandApdu): CompletionResult<ResponseApdu> =
            suspendCancellableCoroutine { continuation ->
                transceiveApdu(apdu) { result ->
                    if (continuation.isActive) continuation.resume(result) {}
                }
            }


    override fun transceiveApdu(apdu: CommandApdu, callback: (response: CompletionResult<ResponseApdu>) -> Unit) {
        val channel = channel ?: throw IOException()

        Log.nfc { "Sending data to the card, size is ${apdu.apduData.size}" }
        Log.nfc { "Raw data that is to be sent to the card: ${apdu.apduData.toHexString()}" }

        val rawResponse: ByteArray? = try {
            val rspAPDU = channel.transmit(CommandAPDU(apdu.apduData))
            rspAPDU.bytes
        } catch (e: Exception) {
            callback.invoke(CompletionResult.Failure(TangemSdkError.TagLost()))
            if (terminal?.waitForCardAbsent(30000) == true) {
                connectedTag = null
                startSession()
            } else {
                Log.error { e.localizedMessage }
                stopSession()
            }
            return
        }

        if (rawResponse != null) {
            Log.nfc { "Data from the card was received" }
            Log.nfc { "Raw data that was received from the card: ${rawResponse.toHexString()}" }
            callback.invoke(CompletionResult.Success(ResponseApdu(rawResponse)))
        }
    }
}