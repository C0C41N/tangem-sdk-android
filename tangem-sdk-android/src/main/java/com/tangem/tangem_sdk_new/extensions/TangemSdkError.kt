package com.tangem.tangem_sdk_new.extensions

import android.content.Context
import com.tangem.common.core.TangemSdkError
import com.tangem.tangem_sdk_new.R

fun TangemSdkError.localizedDescription(context: Context): String {
    val resId = when (this) {
        is TangemSdkError.EncodingFailedTypeMismatch, is TangemSdkError.EncodingFailed,
        is TangemSdkError.DecodingFailedMissingTag, is TangemSdkError.DecodingFailedTypeMismatch,
        is TangemSdkError.DecodingFailed, is TangemSdkError.CryptoUtilsError, is TangemSdkError.NetworkError,
        is TangemSdkError.ExceptionError, is TangemSdkError.HDWalletDisabled, is TangemSdkError.FileSettingsUnsupported,
        is TangemSdkError.FilesDisabled, is TangemSdkError.FilesIsEmpty -> null

        is TangemSdkError.TagLost -> R.string.error_tag_lost
        is TangemSdkError.ExtendedLengthNotSupported -> R.string.error_operation
        is TangemSdkError.SerializeCommandError -> R.string.error_operation
        is TangemSdkError.DeserializeApduFailed -> R.string.error_operation
        is TangemSdkError.UnknownStatus -> R.string.error_operation
        is TangemSdkError.ErrorProcessingCommand -> R.string.error_operation
        is TangemSdkError.InvalidState -> R.string.error_operation
        is TangemSdkError.InsNotSupported -> R.string.error_operation
        is TangemSdkError.InvalidParams -> R.string.error_operation
        is TangemSdkError.NeedEncryption -> R.string.error_operation
        is TangemSdkError.FileNotFound -> R.string.error_operation
        is TangemSdkError.AlreadyPersonalized -> R.string.error_already_personalized
        is TangemSdkError.CannotBeDepersonalized -> R.string.error_cannot_be_depersonalized
        is TangemSdkError.AccessCodeRequired -> R.string.error_operation
        is TangemSdkError.AlreadyCreated -> R.string.error_already_created
        is TangemSdkError.PurgeWalletProhibited -> R.string.error_purge_prohibited
        is TangemSdkError.AccessCodeCannotBeChanged -> R.string.error_pin1_cannot_be_changed
        is TangemSdkError.PasscodeCannotBeChanged -> R.string.error_pin2_cannot_be_changed
        is TangemSdkError.AccessCodeCannotBeDefault -> R.string.error_pin1_cannot_be_default
        is TangemSdkError.NoRemainingSignatures -> R.string.error_no_remaining_signatures
        is TangemSdkError.EmptyHashes -> R.string.error_empty_hashes
        is TangemSdkError.HashSizeMustBeEqual -> R.string.error_cannot_be_signed
        is TangemSdkError.WalletIsNotCreated -> R.string.error_wallet_is_not_created
        is TangemSdkError.WalletIsPurged -> R.string.error_wallet_is_purged
        is TangemSdkError.SignHashesNotAvailable -> R.string.error_cannot_be_signed
        is TangemSdkError.TooManyHashesInOneTransaction -> R.string.error_cannot_be_signed
        is TangemSdkError.NotPersonalized -> R.string.error_not_personalized
        is TangemSdkError.NotActivated -> R.string.error_not_activated
        is TangemSdkError.PasscodeRequired -> R.string.error_operation
        is TangemSdkError.VerificationFailed -> R.string.error_verification_failed
        is TangemSdkError.DataSizeTooLarge -> R.string.error_data_size_too_large
        is TangemSdkError.ExtendedDataSizeTooLarge -> R.string.error_data_size_too_large_extended
        is TangemSdkError.MissingCounter -> R.string.error_missing_counter
        is TangemSdkError.OverwritingDataIsProhibited -> R.string.error_data_cannot_be_written
        is TangemSdkError.DataCannotBeWritten -> R.string.error_data_cannot_be_written
        is TangemSdkError.MissingIssuerPubicKey -> R.string.error_missing_issuer_public_key
        is TangemSdkError.CardVerificationFailed -> R.string.error_card_verification_failed
        is TangemSdkError.UnknownError -> R.string.error_operation
        is TangemSdkError.UserCancelled -> R.string.error_user_cancelled
        is TangemSdkError.Busy -> R.string.error_busy
        is TangemSdkError.MissingPreflightRead -> R.string.error_operation
        is TangemSdkError.WrongCardNumber -> R.string.error_wrong_card_number
        is TangemSdkError.WrongCardType -> R.string.error_wrong_card_type
        is TangemSdkError.CardError -> R.string.error_card_error
        is TangemSdkError.InvalidResponse -> R.string.error_invalid_response
        is TangemSdkError.NotSupportedFirmwareVersion -> R.string.error_old_firmware
        is TangemSdkError.MaxNumberOfWalletsCreated -> R.string.error_no_space_for_new_wallet
        is TangemSdkError.CardReadWrongWallet -> R.string.error_card_read_wrong_wallet
        is TangemSdkError.UnsupportedCurve -> R.string.error_wallet_index_exceeds_max_value
        is TangemSdkError.WalletNotFound -> R.string.error_wallet_not_found
        is TangemSdkError.WrongAccessCode -> R.string.error_wrong_pin1
        is TangemSdkError.WrongPasscode -> R.string.error_wrong_pin2
        is TangemSdkError.CardWithMaxZeroWallets -> R.string.error_card_with_max_zero_wallets
        is TangemSdkError.WalletError -> R.string.error_wallet_error
        is TangemSdkError.UnsupportedWalletConfig -> R.string.error_wallet_index_not_correct
        is TangemSdkError.WalletCannotBeCreated -> R.string.error_wallet_cannot_be_created
        is TangemSdkError.AccessCodeOrPasscodeRequired -> null
        is TangemSdkError.BackupCardAlreadyAdded -> null
        is TangemSdkError.BackupCardRequired -> null
        is TangemSdkError.BackupFailedCardNotLinked -> null
        is TangemSdkError.BackupFailedEmptyWallets -> null
        is TangemSdkError.BackupFailedHDWalletSettings -> null
        is TangemSdkError.BackupFailedNotEmptyWallets -> null
        is TangemSdkError.BackupFailedNotEnoughCurves -> null
        is TangemSdkError.BackupFailedNotEnoughWallets -> null
        is TangemSdkError.BackupFailedWrongIssuer -> null
        is TangemSdkError.BackupNotAllowed -> null
        is TangemSdkError.BackupServiceInvalidState -> null
        is TangemSdkError.CertificateSignatureRequired -> null
        is TangemSdkError.EmptyBackupCards -> null
        is TangemSdkError.MissingPrimaryAttestSignature -> null
        is TangemSdkError.MissingPrimaryCard -> null
        is TangemSdkError.NoActiveBackup -> null
        is TangemSdkError.NoBackupCardForIndex -> null
        is TangemSdkError.NoBackupDataForCard -> null
        is TangemSdkError.ResetBackupFailedHasBackupedWallets -> null
        is TangemSdkError.ResetPinNoCardsToReset -> null
        is TangemSdkError.ResetPinWrongCard -> null
        is TangemSdkError.TooMuchBackupCards -> null
        is TangemSdkError.WrongInteractionMode -> R.string.error_wrong_interaction_mode
        is TangemSdkError.IssuerSignatureLoadingFailed -> null
    }

    return if (resId != null) {
        context.getString(resId)
    } else {
        context.getString(R.string.error_operation)
    }
}