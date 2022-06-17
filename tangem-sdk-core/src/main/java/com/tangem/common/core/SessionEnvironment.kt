package com.tangem.common.core

import com.tangem.common.KeyPair
import com.tangem.common.UserCode
import com.tangem.common.UserCodeType
import com.tangem.common.accesscode.AccessCodeRepository
import com.tangem.common.accesscode.DummyAccessCodeRepository
import com.tangem.common.auth.AuthManager
import com.tangem.common.auth.DummyAuthManager
import com.tangem.common.card.Card
import com.tangem.common.card.EncryptionMode
import com.tangem.common.card.WalletData
import com.tangem.common.extensions.calculateSha256
import com.tangem.common.services.InMemoryStorage
import com.tangem.common.services.Storage
import com.tangem.common.services.secure.SecureStorage
import com.tangem.common.services.secure.TerminalKeysService
import com.tangem.common.services.secure.TerminalKeysStorage
import java.lang.ref.WeakReference


/**
 * Contains data relating to a Tangem card. It is used in constructing all the commands,
 * and commands can return modified `SessionEnvironment`.
 */
class SessionEnvironment(
    val config: Config,
    val secureStorage: SecureStorage,
    val storage: Storage,
    val accessCodeRepository: AccessCodeRepository,
    val authManager: AuthManager,
) {
    /**
     * Current card, read by preflight `Read` command
     */
    var card: Card? = null
    var walletData: WalletData? = null


    var terminalKeysService: WeakReference<TerminalKeysService> = WeakReference(TerminalKeysStorage(secureStorage))
    var encryptionMode: EncryptionMode = EncryptionMode.None
    var encryptionKey: ByteArray? = null
    var cvc: ByteArray? = null
    var accessCode: UserCode = UserCode(UserCodeType.AccessCode)
    var passcode: UserCode = UserCode(UserCodeType.Passcode)

    /**
     * Keys for Linked Terminal feature
     */
    var terminalKeys: KeyPair? = if (config.linkedTerminal == true) {
        terminalKeysService.get()?.getKeys()
    } else {
        null
    }

    fun isUserCodeSet(type: UserCodeType): Boolean {
        return when (type) {
            UserCodeType.AccessCode -> accessCode.value?.contentEquals(type.defaultValue.calculateSha256()) == false
            UserCodeType.Passcode -> passcode.value?.contentEquals(type.defaultValue.calculateSha256()) == false
        }
    }

    @Deprecated("Used to fix lack of security delay on cards with firmware version below 1.21")
    var enableMissingSecurityDelay: Boolean = false

    companion object {
        @Deprecated("Used to fix lack of security delay on cards with firmware version below 1.21")
        val missingSecurityDelayCode = -100100

        val Dummy = SessionEnvironment(
            config = Config(),
            storage = InMemoryStorage(),
            secureStorage = InMemoryStorage(),
            authManager = DummyAuthManager(),
            accessCodeRepository = DummyAccessCodeRepository()
        )
    }
}
