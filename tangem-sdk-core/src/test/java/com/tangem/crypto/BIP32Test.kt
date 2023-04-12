package com.tangem.crypto

import com.tangem.common.card.EllipticCurve
import com.tangem.common.extensions.hexToBytes
import com.tangem.common.extensions.toHexString
import com.tangem.common.successOrNull
import com.tangem.crypto.bip39.BIP39Wordlist
import com.tangem.crypto.bip39.BIP39WordlistTest
import com.tangem.crypto.bip39.DefaultMnemonic
import com.tangem.crypto.bip39.Wordlist
import com.tangem.crypto.hdWallet.DerivationNode
import com.tangem.crypto.hdWallet.bip32.BIP32
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.InputStream

internal class BIP32Test {

    init {
        CryptoUtils.initCrypto()
    }

    @Test
    fun testSecp256k1MasterKeyGeneration() {
        val masterKey = BIP32.makeMasterKey("000102030405060708090a0b0c0d0e0f".hexToBytes(), EllipticCurve.Secp256k1)
        assertEquals(
            masterKey.privateKey.toHexString().lowercase(),
            "e8f32e723decf4051aefac8e2c93c9c5b214313817cdb01a1494b917c8436b35".lowercase(),
        )
        assertEquals(
            masterKey.chainCode.toHexString().lowercase(),
            "873dff81c02f525623fd1fe5167eac3a55a049de3d314bb42ee227ffed37d508".lowercase(),
        )

        val masterKey2 = BIP32.makeMasterKey(
            "fffcf9f6f3f0edeae7e4e1dedbd8d5d2cfccc9c6c3c0bdbab7b4b1aeaba8a5a29f9c999693908d8a8784817e7b7875726f6c696663605d5a5754514e4b484542".hexToBytes(),
            EllipticCurve.Secp256k1,
        )
        assertEquals(
            masterKey2.privateKey.toHexString().lowercase(),
            "4b03d6fc340455b363f51020ad3ecca4f0850280cf436c70c727923f6db46c3e".lowercase(),
        )
        assertEquals(
            masterKey2.chainCode.toHexString().lowercase(),
            "60499f801b896d83179a4374aeb7822aaeaceaa0db1f85ee3e904c4defbd9689".lowercase(),
        )
    }

    @Test
    fun testSecp256r1MasterKeyGeneration() {
        val masterKey = BIP32.makeMasterKey("000102030405060708090a0b0c0d0e0f".hexToBytes(), EllipticCurve.Secp256r1)
        assertEquals(
            masterKey.privateKey.toHexString().lowercase(),
            "612091aaa12e22dd2abef664f8a01a82cae99ad7441b7ef8110424915c268bc2".lowercase(),
        )
        assertEquals(
            masterKey.chainCode.toHexString().lowercase(),
            "beeb672fe4621673f722f38529c07392fecaa61015c80c34f29ce8b41b3cb6ea".lowercase(),
        )

        val masterKey2 =
            BIP32.makeMasterKey(
                "fffcf9f6f3f0edeae7e4e1dedbd8d5d2cfccc9c6c3c0bdbab7b4b1aeaba8a5a29f9c999693908d8a8784817e7b7875726f6c696663605d5a5754514e4b484542".hexToBytes(),
                EllipticCurve.Secp256r1,
            )
        assertEquals(
            masterKey2.privateKey.toHexString().lowercase(),
            "eaa31c2e46ca2962227cf21d73a7ef0ce8b31c756897521eb6c7b39796633357".lowercase(),
        )
        assertEquals(
            masterKey2.chainCode.toHexString().lowercase(),
            "96cd4465a9644e31528eda3592aa35eb39a9527769ce1855beafc1b81055e75d".lowercase(),
        )
    }

    @Test
    fun testEd25519MasterKeyGeneration() {
        val masterKey =
            BIP32.makeMasterKey("000102030405060708090a0b0c0d0e0f".hexToBytes(), EllipticCurve.Ed25519)
        assertEquals(
            masterKey.privateKey.toHexString().lowercase(),
            "2b4be7f19ee27bbf30c667b642d5f4aa69fd169872f8fc3059c08ebae2eb19e7".lowercase(),
        )
        assertEquals(
            masterKey.chainCode.toHexString().lowercase(),
            "90046a93de5380a72b5e45010748567d5ea02bbf6522f979e05c0d8d8ca9fffb".lowercase(),
        )

        val masterKey2 =
            BIP32.makeMasterKey(
                "fffcf9f6f3f0edeae7e4e1dedbd8d5d2cfccc9c6c3c0bdbab7b4b1aeaba8a5a29f9c999693908d8a8784817e7b7875726f6c696663605d5a5754514e4b484542".hexToBytes(),
                EllipticCurve.Ed25519,
            )
        assertEquals(
            masterKey2.privateKey.toHexString().lowercase(),
            "171cb88b1b3c1db25add599712e36245d75bc65a1a5c9e18d76f9f2b1eab4012".lowercase(),
        )
        assertEquals(
            masterKey2.chainCode.toHexString().lowercase(),
            "ef70a74db9c3a5af931b5fe73ed8e1a53464133654fd55e7a66f8570b8e33c3b".lowercase(),
        )
    }

    @Test
    fun testSecp256r1MasterKeyGenerationRetry() {
        val masterKey = BIP32.makeMasterKey(
            "a7305bc8df8d0951f0cb224c0e95d7707cbdf2c6ce7e8d481fec69c7ff5e9446".hexToBytes(),
            EllipticCurve.Secp256r1,
        )
        assertEquals(
            masterKey.privateKey.toHexString().lowercase(),
            "3b8c18469a4634517d6d0b65448f8e6c62091b45540a1743c5846be55d47d88f".lowercase(),
        )
        assertEquals(
            masterKey.chainCode.toHexString().lowercase(),
            "7762f9729fed06121fd13f326884c82f59aa95c57ac492ce8c9654e60efd130c".lowercase(),
        )
    }

    @Test
    fun testMetaMaskTWCompatible() {
        val mnemonicPhrase = "scale wave venue cloth fruit empower afford one domain blouse romance artist"
        val mnemonic = DefaultMnemonic(mnemonicPhrase, createDefaultWordlist())
        val seed: ByteArray = mnemonic.generateSeed().successOrNull()!!
        assertEquals(
            seed.toHexString().lowercase(),
            "d3eea633215dc4cb8ec2acd0d413adec1ebccb597ecf279886e584e9cb9ceb0788eb6f17a585acc12bc58fd586df6bbbdf39af955656f24215cceab174344e62".lowercase(),
        )

        val extendedPrivateKey = BIP32.makeMasterKey(seed, EllipticCurve.Secp256k1)

        val pk = extendedPrivateKey.privateKey.toHexString().lowercase()
        assertEquals(pk, "589aeb596710f33d7ac31598ec10440a7df8808cf2c3d69ba670ff3fae66aafb".lowercase())

        assertEquals(
            extendedPrivateKey.serializeToWIFCompressed(NetworkType.Mainnet),
            "KzBwvPW6L5iwJSiE5vgS52Y69bUxfwizW3wF4C4Xa3ba3pdd7j63",
        )
    }

    @Test
    fun testMasterVector1() {
        val seed = "000102030405060708090a0b0c0d0e0f".hexToBytes()
        val mPriv = BIP32.makeMasterKey(seed, EllipticCurve.Secp256k1)
        val mPub = mPriv.makePublicKey(EllipticCurve.Secp256k1)

        val xpriv = mPriv.serialize(NetworkType.Mainnet)
        assertEquals(
            xpriv,
            "xprv9s21ZrQH143K3QTDL4LXw2F7HEK3wJUD2nW2nRk4stbPy6cq3jPPqjiChkVvvNKmPGJxWUtg6LnF5kejMRNNU3TGtRBeJgk33yuGBxrMPHi",
        )

        val xpub = mPub.serialize(NetworkType.Mainnet)
        assertEquals(
            xpub,
            "xpub661MyMwAqRbcFtXgS5sYJABqqG9YLmC4Q1Rdap9gSE8NqtwybGhePY2gZ29ESFjqJoCu1Rupje8YtGqsefD265TMg7usUDFdp6W1EGMcet8",
        )
    }

    @Test
    fun testMasterVector2() {
        val seed =
            "fffcf9f6f3f0edeae7e4e1dedbd8d5d2cfccc9c6c3c0bdbab7b4b1aeaba8a5a29f9c999693908d8a8784817e7b7875726f6c696663605d5a5754514e4b484542".hexToBytes()

        val mPriv = BIP32.makeMasterKey(seed, EllipticCurve.Secp256k1)
        val mPub = mPriv.makePublicKey(EllipticCurve.Secp256k1)

        val xpriv = mPriv.serialize(NetworkType.Mainnet)
        assertEquals(
            xpriv,
            "xprv9s21ZrQH143K31xYSDQpPDxsXRTUcvj2iNHm5NUtrGiGG5e2DtALGdso3pGz6ssrdK4PFmM8NSpSBHNqPqm55Qn3LqFtT2emdEXVYsCzC2U",
        )

        val xpub = mPub.serialize(NetworkType.Mainnet)
        assertEquals(
            xpub,
            "xpub661MyMwAqRbcFW31YEwpkMuc5THy2PSt5bDMsktWQcFF8syAmRUapSCGu8ED9W6oDMSgv6Zz8idoc4a6mr8BDzTJY47LJhkJ8UB7WEGuduB",
        )

        // Chain m/0
        val derivedPub = mPub.derivePublicKey(DerivationNode.NonHardened(0))
        val derivedXPub = derivedPub.serialize(NetworkType.Mainnet)
        assertEquals(
            derivedXPub,
            "xpub69H7F5d8KSRgmmdJg2KhpAK8SR3DjMwAdkxj3ZuxV27CprR9LgpeyGmXUbC6wb7ERfvrnKZjXoUmmDznezpbZb7ap6r1D3tgFxHmwMkQTPH",
        )
    }

    @Test
    fun testMasterVector3() {
        val seed =
            "4b381541583be4423346c643850da4b320e46a87ae3d2a4e6da11eba819cd4acba45d239319ac14f863b8d5ab5a0d0c64d2e8a1e7d1457df2e5a3c51c73235be".hexToBytes()

        val mPriv = BIP32.makeMasterKey(seed, EllipticCurve.Secp256k1)
        val mPub = mPriv.makePublicKey(EllipticCurve.Secp256k1)

        val xpriv = mPriv.serialize(NetworkType.Mainnet)
        assertEquals(
            xpriv,
            "xprv9s21ZrQH143K25QhxbucbDDuQ4naNntJRi4KUfWT7xo4EKsHt2QJDu7KXp1A3u7Bi1j8ph3EGsZ9Xvz9dGuVrtHHs7pXeTzjuxBrCmmhgC6",
        )
        val xpub = mPub.serialize(NetworkType.Mainnet)
        assertEquals(
            xpub,
            "xpub661MyMwAqRbcEZVB4dScxMAdx6d4nFc9nvyvH3v4gJL378CSRZiYmhRoP7mBy6gSPSCYk6SzXPTf3ND1cZAceL7SfJ1Z3GC8vBgp2epUt13",
        )
    }

    @Test
    fun testMasterVector4() {
        val seed = "3ddd5602285899a946114506157c7997e5444528f3003f6134712147db19b678".hexToBytes()

        val mPriv = BIP32.makeMasterKey(seed, EllipticCurve.Secp256k1)
        val mPub = mPriv.makePublicKey(EllipticCurve.Secp256k1)

        val xpriv = mPriv.serialize(NetworkType.Mainnet)
        assertEquals(
            xpriv,
            "xprv9s21ZrQH143K48vGoLGRPxgo2JNkJ3J3fqkirQC2zVdk5Dgd5w14S7fRDyHH4dWNHUgkvsvNDCkvAwcSHNAQwhwgNMgZhLtQC63zxwhQmRv",
        )

        val xpub = mPub.serialize(NetworkType.Mainnet)
        assertEquals(
            xpub,
            "xpub661MyMwAqRbcGczjuMoRm6dXaLDEhW1u34gKenbeYqAix21mdUKJyuyu5F1rzYGVxyL6tmgBUAEPrEz92mBXjByMRiJdba9wpnN37RLLAXa",
        )
    }

    private fun createDefaultWordlist(): Wordlist {
        val wordlistStream = getInputStreamForFile(BIP39WordlistTest.TEST_DICTIONARY_FILE_NAME)
        return BIP39Wordlist(wordlistStream)
    }

    private fun getInputStreamForFile(fileName: String): InputStream {
        return object {}.javaClass.classLoader.getResourceAsStream(fileName)!!
    }
}
