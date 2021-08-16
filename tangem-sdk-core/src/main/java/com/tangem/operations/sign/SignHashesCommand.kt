package com.tangem.operations.sign

import com.tangem.common.core.CardSession
import com.tangem.common.core.CardSessionRunnable
import com.tangem.common.core.CompletionCallback
import com.tangem.common.hdWallet.DerivationPath
import com.tangem.operations.PreflightReadMode

/**
 * Signs transaction hash using a wallet private key, stored on the card.
 * @property hashes Array of transaction hashes.
 * @property walletPublicKey: Public key of the wallet, using for sign.
 * @property hdPath: Derivation path of the wallet. Optional. COS v. 4.28 and higher,
 */
typealias SignHashesResponse = SignResponse

class SignHashesCommand(
    private val hashes: Array<ByteArray>,
    private val walletPublicKey: ByteArray,
    private val hdPath: DerivationPath? = null
) : CardSessionRunnable<SignHashesResponse> {

    override fun preflightReadMode(): PreflightReadMode = PreflightReadMode.ReadWallet(walletPublicKey)

    override fun run(session: CardSession, callback: CompletionCallback<SignHashesResponse>) {
        SignCommand(hashes, walletPublicKey, hdPath).run(session, callback)
    }
}