package com.tangem.common.apdu

import com.tangem.Log
import com.tangem.common.core.TangemSdkError
import com.tangem.common.extensions.calculateCrc16
import com.tangem.common.extensions.toHexString
import com.tangem.common.tlv.Tlv
import com.tangem.common.tlv.TlvTag
import com.tangem.crypto.decrypt
import com.tangem.crypto.decryptAesCcm
import java.io.ByteArrayInputStream

/**
 * Stores response data from the card and parses it to [Tlv] and [StatusWord].
 *
 * @property data Raw response from the card.
 * @property sw Status word code, reflecting the status of the response.
 * @property statusWord Parsed status word.
 */
class ResponseApdu(private val data: ByteArray) {

    private val sw1: Int = 0x00FF and data[data.size - 2].toInt()
    private val sw2: Int = 0x00FF and data[data.size - 1].toInt()

    val sw: Int = sw1 shl 8 or sw2

    val statusWord: StatusWord = StatusWord.byCode(sw)

    /**
     * Converts raw response data to the list of TLVs.
     *
     * (Encryption / decryption functionality is not implemented yet.)
     */
    fun getTlvData(): List<Tlv>? {
        return if (data.size <= 2) {
            null
        } else {
            Tlv.deserialize(data.copyOf(data.size - 2))
        }
    }

    @Suppress("MagicNumber")
    fun decrypt(encryptionKey: ByteArray?): ResponseApdu {
        if (encryptionKey == null) return this

        // nothing to decrypt
        if (data.size < 18) return this

        val responseData = data.copyOf(data.size - 2)

        val decryptedData: ByteArray = responseData.decrypt(encryptionKey)

        val inputStream = ByteArrayInputStream(decryptedData)
        val baLength = ByteArray(2)
        inputStream.read(baLength)
        val length = (baLength[0].toInt() and 0xFF) * 256 + (baLength[1].toInt() and 0xFF)
        if (length > decryptedData.size - 4) throw TangemSdkError.InvalidResponse()
        val baCRC = ByteArray(2)
        inputStream.read(baCRC)
        val answerData = ByteArray(length)
        inputStream.read(answerData)
        val crc: ByteArray = answerData.calculateCrc16()
        if (!baCRC.contentEquals(crc)) throw TangemSdkError.InvalidResponse()

        return ResponseApdu(answerData + data[data.size - 2] + data[data.size - 1])
    }

    @Suppress("MagicNumber")
    fun decryptCcm(encryptionKey: ByteArray?, nonce: ByteArray, cardId: ByteArray): ResponseApdu {
        if (encryptionKey == null) return this
        return if (data.size == 2) {
            ResponseApdu(data)
        } else if (data.size >= 10) {
            val associatedData = data.copyOfRange(data.size - 2, data.size)
            val answerData = data.copyOfRange(0, data.size - 2).decryptAesCcm(encryptionKey, nonce, associatedData = associatedData)
            //TODO - remove workaround with add CardId to answer
            return ResponseApdu(answerData + byteArrayOf(TlvTag.CardId.code.toByte(), cardId.size.toByte()) + cardId + associatedData)
        } else {
            throw Exception("Can't decrypt - data size to small")
        }
    }

    @Suppress("MagicNumber")
    fun decryptCcm(encryptionKey: ByteArray?): ResponseApdu {
        if (encryptionKey == null) return this
        return if (data.size == 2) {
            ResponseApdu(data)
        } else if (data.size >= 14) {
            val nonce = data.copyOfRange(0, 12)
            Log.info { "Nonce: ${nonce.toHexString()}" }
            val answerData = data.copyOfRange(12, data.size - 2).decryptAesCcm(
                encryptionKey, nonce,
                data.copyOfRange(data.size - 2, data.size)
            )
            Log.info { "Decrypted answer: ${answerData.toHexString()}" }

            ResponseApdu(answerData + data.copyOfRange(data.size - 2, data.size))
        } else {
            throw Exception("Can't decrypt - data size to small")
        }
    }

    override fun toString(): String {
        return "<<<< [${data.size + 2} bytes]: ${data.toHexString()} $sw1 $sw2 (SW: $statusWord)"
    }
}
