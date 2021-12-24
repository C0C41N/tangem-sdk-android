package com.tangem.common.deserialization

import com.tangem.common.card.CardWallet
import com.tangem.common.card.EllipticCurve
import com.tangem.common.core.Secp256k1KeyFormat
import com.tangem.common.core.TangemSdkError
import com.tangem.common.tlv.Tlv
import com.tangem.common.tlv.TlvDecoder
import com.tangem.common.tlv.TlvTag

/**
 * Deserialize v4 wallets only
 * @property isDefaultPermanentWallet - newest v4 cards don't have their own wallet settings,
 * so we should take them from the card's settings
 */
internal class WalletDeserializer(
    private val isDefaultPermanentWallet: Boolean,
    private val secp256k1KeyFormat: Secp256k1KeyFormat
) {

    internal fun deserializeWallets(decoder: TlvDecoder): DeserializedWallets {
        val cardWalletsData: List<ByteArray> = decoder.decodeArray(TlvTag.CardWallet)
        if (cardWalletsData.isEmpty()) throw TangemSdkError.DeserializeApduFailed()

        val walletsDecoders = cardWalletsData.mapNotNull { walletData ->
            Tlv.deserialize(walletData)?.let { tlvs -> TlvDecoder(tlvs) }
        }
        val wallets = walletsDecoders.mapNotNull { deserializeWallet(it) }

        return Pair(wallets, cardWalletsData.size)
    }

    internal fun deserializeWallet(decoder: TlvDecoder): CardWallet? {
        val status: CardWallet.Status? = decoder.decode(TlvTag.Status)
        return if (status == CardWallet.Status.Loaded || status == CardWallet.Status.Backuped) {
            deserialize(decoder, status)
        } else {
            null
        }
    }

    private fun deserialize(decoder: TlvDecoder, status: CardWallet.Status): CardWallet {
        val walletSettingsMask: CardWallet.SettingsMask? =
            decoder.decodeOptional(TlvTag.SettingsMask)

        val settings = if (walletSettingsMask != null) {
            CardWallet.Settings(walletSettingsMask)
        } else {
            //Newest v4 cards don't have their own wallet settings, so we should take them from the card's settings
            CardWallet.Settings(isDefaultPermanentWallet)
        }

        val walletPublicKey: ByteArray = decoder.decode(TlvTag.WalletPublicKey)
        val curve: EllipticCurve = decoder.decode(TlvTag.CurveId)
        val key = if (curve == EllipticCurve.Secp256k1) {
            secp256k1KeyFormat.format(walletPublicKey)
        } else {
            walletPublicKey
        }

        return CardWallet(
            publicKey = key,
            chainCode = decoder.decodeOptional(TlvTag.WalletHDChain),
            curve = curve,
            settings = settings,
            totalSignedHashes = decoder.decode(TlvTag.WalletSignedHashes),
            remainingSignatures = null,
            index = decoder.decode(TlvTag.WalletIndex),
            hasBackup = status == CardWallet.Status.Backuped
        )
    }
}

typealias DeserializedWallets = Pair<List<CardWallet>, Int>