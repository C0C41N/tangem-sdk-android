package com.tangem.tasks

import com.tangem.*
import com.tangem.commands.*
import com.tangem.commands.common.card.FirmwareVersion
import com.tangem.commands.wallet.*
import com.tangem.common.CompletionResult
import com.tangem.common.extensions.guard

/**
 * Task that allows to create wallet at Tangem card and verify its private key.
 * It performs `CreateWallet` and `CheckWalletCommand`,  subsequently.
 *
 * Initializer contain wallet configuration settings and wallet index pointer at which  index wallet should be created.
 * If index not provided task
 * attempt to create wallet at any empty index, until success or reach max index
 *
 * Note: `WalletConfig` and `WalletIndexValue` available for cards with COS v.4.0 and higher.
 * @property config: if not set task will create wallet with settings that was specified in card data
 * while personalization
 * @property walletIndexValue: If not provided task will attempt to create wallet on default index.
 * If failed - task will keep trying to create.
 */
class CreateWalletTask(
    private val config: WalletConfig? = null
) : CardSessionRunnable<CreateWalletResponse> {

    @Deprecated("Missing calls", ReplaceWith("Nothing"))
    fun requiresPin2(): Boolean = true

    override fun preflightReadMode(): PreflightReadMode = PreflightReadMode.FullCardRead

    override fun run(session: CardSession, callback: (result: CompletionResult<CreateWalletResponse>) -> Unit) {
        var card = session.environment.card.guard {
            callback(CompletionResult.Failure(TangemSdkError.CardError()))
            return
        }
        var curve = card.defaultCurve.guard {
            callback(CompletionResult.Failure(TangemSdkError.CardError()))
            return
        }

        val firmwareVersion = session.environment.card?.firmwareVersion ?: FirmwareVersion.zero
        if (firmwareVersion >= FirmwareConstraints.AvailabilityVersions.walletData) {
            config?.curveId?.let { curve = it }
        }

        val emptyWallet = card.wallets.firstOrNull { it.status == WalletStatus.Empty }.guard {
            callback(CompletionResult.Failure(TangemSdkError.MaxNumberOfWalletsCreated()))
            return
        }

        Log.debug { "------ Found empty wallet $emptyWallet. Attempting to create wallet ---------" }
        CreateWalletCommand(config, emptyWallet.index).run(session) { result ->
            when (result) {
                is CompletionResult.Success -> {
                    card = card.copy(status = result.data.status)
                    card.settingsMask?.let { card = card.updateWallet(CardWallet(result.data, curve, it)) }
                    session.environment.card = card
                    callback(CompletionResult.Success(result.data))
                }
                is CompletionResult.Failure -> callback(CompletionResult.Failure(result.error))
            }
        }
    }
}