package com.tangem.operations

import com.tangem.Log
import com.tangem.common.CompletionResult
import com.tangem.common.card.Card
import com.tangem.common.card.FirmwareVersion
import com.tangem.common.core.CardSession
import com.tangem.common.core.CardSessionRunnable
import com.tangem.common.core.CompletionCallback
import com.tangem.common.core.TangemSdkError
import com.tangem.common.extensions.guard
import com.tangem.common.extensions.toHexString
import com.tangem.operations.read.ReadCommand
import com.tangem.operations.read.ReadWalletCommand
import com.tangem.operations.read.ReadWalletsListCommand

/**
 * Mode for preflight read task
 * Note: Valid for cards with COS v. 4.0 and higher. Older card will always read the card and the wallet info.
 * `fullCardRead` will be used by default
 */
sealed class PreflightReadMode {

    /**
     * No card will be read at session start. `SessionEnvironment.card` will be empty
     */
    object None : PreflightReadMode()

    /**
     * Read only card info without wallet info. Valid for cards with COS v. 4.0 and higher.
     * Older card will always read card and wallet info
     */
    object ReadCardOnly : PreflightReadMode()

    /**
     * Read card info and single wallet specified in associated index `WalletIndex`.
     * Valid for cards with COS v. 4.0 and higher. Older card will always read card and wallet info
     */
    class ReadWallet(val publicKey: ByteArray) : PreflightReadMode() {
        override fun toString(): String = publicKey.toHexString()
    }

    /**
     * Read card info and all wallets. Used by default
     */
    object FullCardRead : PreflightReadMode()

    override fun toString(): String = this::class.java.simpleName
}

class PreflightReadTask(
    private val readMode: PreflightReadMode,
    private val cardId: String? = null
) : CardSessionRunnable<Card> {

    override fun run(session: CardSession, callback: CompletionCallback<Card>) {
        Log.debug { "================ Perform preflight check with settings: $readMode) ================" }
        ReadCommand().run(session) { result ->
            when (result) {
                is CompletionResult.Success -> {
                    if (cardId != null && cardId.toUpperCase() != result.data.card.cardId) {
                        callback(CompletionResult.Failure(TangemSdkError.WrongCardNumber()))
                        return@run
                    }
                    if (!session.environment.config.filter.isCardAllowed(result.data.card)) {
                        callback(CompletionResult.Failure(TangemSdkError.WrongCardType()))
                        return@run
                    }
                    finalizeRead(session, result.data.card, callback)
                }
                is CompletionResult.Failure -> callback(CompletionResult.Failure(result.error))
            }
        }
    }

    private fun finalizeRead(session: CardSession, card: Card, callback: CompletionCallback<Card>) {
        if (card.firmwareVersion < FirmwareVersion.MultiWalletAvailable) {
            callback(CompletionResult.Success(card))
            return
        }

        when (readMode) {
            is PreflightReadMode.ReadWallet -> readWallet(readMode.publicKey, session, callback)
            PreflightReadMode.FullCardRead -> readWalletsList(session, callback)
            PreflightReadMode.ReadCardOnly, PreflightReadMode.None -> callback(CompletionResult.Success(card))
        }
    }

    private fun readWallet(publicKey: ByteArray, session: CardSession, callback: CompletionCallback<Card>) {
        ReadWalletCommand(publicKey).run(session) { result ->
            val card = session.environment.card.guard {
                callback(CompletionResult.Failure(TangemSdkError.CardError()))
                return@run
            }
            when (result) {
                is CompletionResult.Success -> callback(CompletionResult.Success(card))
                is CompletionResult.Failure -> callback(CompletionResult.Failure(result.error))
            }
        }
    }

    private fun readWalletsList(session: CardSession, callback: CompletionCallback<Card>) {
        ReadWalletsListCommand().run(session) { result ->
            val card = session.environment.card.guard {
                callback(CompletionResult.Failure(TangemSdkError.CardError()))
                return@run
            }
            when (result) {
                is CompletionResult.Success -> callback(CompletionResult.Success(card))
                is CompletionResult.Failure -> callback(CompletionResult.Failure(result.error))
            }
        }
    }
}