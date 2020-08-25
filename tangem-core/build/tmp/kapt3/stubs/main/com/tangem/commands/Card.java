package com.tangem.commands;

import java.lang.System;

/**
 * Response for [ReadCommand]. Contains detailed card information.
 */
@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b!\u0018\u00002\u00020\u0001B\u00d9\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u001e\u001a\u00020\u0019\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010 \u00a2\u0006\u0002\u0010!R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u001f\u001a\u0004\u0018\u00010 \u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\'R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010.\u001a\u0004\b,\u0010-R\u0011\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010/R\u0013\u0010\f\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010#R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\'R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010.\u001a\u0004\b2\u0010-R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010.\u001a\u0004\b3\u0010-R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010#R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0011\u0010\u001e\u001a\u00020\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010/R\u0015\u0010\u001c\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010.\u001a\u0004\b<\u0010-R\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010.\u001a\u0004\b=\u0010-R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010#R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010.\u001a\u0004\b?\u0010-R\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\n\n\u0002\u0010.\u001a\u0004\b@\u0010-\u00a8\u0006A"}, d2 = {"Lcom/tangem/commands/Card;", "Lcom/tangem/commands/CommandResponse;", "cardId", "", "manufacturerName", "status", "Lcom/tangem/commands/CardStatus;", "firmwareVersion", "cardPublicKey", "", "settingsMask", "Lcom/tangem/commands/SettingsMask;", "issuerPublicKey", "curve", "Lcom/tangem/commands/EllipticCurve;", "maxSignatures", "", "signingMethods", "Lcom/tangem/commands/SigningMethodMask;", "pauseBeforePin2", "walletPublicKey", "walletRemainingSignatures", "walletSignedHashes", "health", "isActivated", "", "activationSeed", "paymentFlowVersion", "userCounter", "userProtectedCounter", "terminalIsLinked", "cardData", "Lcom/tangem/commands/CardData;", "(Ljava/lang/String;Ljava/lang/String;Lcom/tangem/commands/CardStatus;Ljava/lang/String;[BLcom/tangem/commands/SettingsMask;[BLcom/tangem/commands/EllipticCurve;Ljava/lang/Integer;Lcom/tangem/commands/SigningMethodMask;Ljava/lang/Integer;[BLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Z[B[BLjava/lang/Integer;Ljava/lang/Integer;ZLcom/tangem/commands/CardData;)V", "getActivationSeed", "()[B", "getCardData", "()Lcom/tangem/commands/CardData;", "getCardId", "()Ljava/lang/String;", "getCardPublicKey", "getCurve", "()Lcom/tangem/commands/EllipticCurve;", "getFirmwareVersion", "getHealth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "getIssuerPublicKey", "getManufacturerName", "getMaxSignatures", "getPauseBeforePin2", "getPaymentFlowVersion", "getSettingsMask", "()Lcom/tangem/commands/SettingsMask;", "getSigningMethods", "()Lcom/tangem/commands/SigningMethodMask;", "getStatus", "()Lcom/tangem/commands/CardStatus;", "getTerminalIsLinked", "getUserCounter", "getUserProtectedCounter", "getWalletPublicKey", "getWalletRemainingSignatures", "getWalletSignedHashes", "tangem-core"})
public final class Card implements com.tangem.commands.CommandResponse {
    
    /**
     * Unique Tangem card ID number.
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String cardId = null;
    
    /**
     * Name of Tangem card manufacturer.
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String manufacturerName = null;
    
    /**
     * Current status of the card.
     */
    @org.jetbrains.annotations.Nullable()
    private final com.tangem.commands.CardStatus status = null;
    
    /**
     * Version of Tangem COS.
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String firmwareVersion = null;
    
    /**
     * Public key that is used to authenticate the card against manufacturer’s database.
     * It is generated one time during card manufacturing.
     */
    @org.jetbrains.annotations.Nullable()
    private final byte[] cardPublicKey = null;
    
    /**
     * Card settings defined by personalization (bit mask: 0 – Enabled, 1 – Disabled).
     */
    @org.jetbrains.annotations.Nullable()
    private final com.tangem.commands.SettingsMask settingsMask = null;
    
    /**
     * Public key that is used by the card issuer to sign IssuerData field.
     */
    @org.jetbrains.annotations.Nullable()
    private final byte[] issuerPublicKey = null;
    
    /**
     * Explicit text name of the elliptic curve used for all wallet key operations.
     * Supported curves: ‘secp256k1’ and ‘ed25519’.
     */
    @org.jetbrains.annotations.Nullable()
    private final com.tangem.commands.EllipticCurve curve = null;
    
    /**
     * Total number of signatures allowed for the wallet when the card was personalized.
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer maxSignatures = null;
    
    /**
     * Defines what data should be submitted to SIGN command.
     */
    @org.jetbrains.annotations.Nullable()
    private final com.tangem.commands.SigningMethodMask signingMethods = null;
    
    /**
     * Delay in seconds before COS executes commands protected by PIN2.
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer pauseBeforePin2 = null;
    
    /**
     * Public key of the blockchain wallet.
     */
    @org.jetbrains.annotations.Nullable()
    private final byte[] walletPublicKey = null;
    
    /**
     * Remaining number of [SignCommand] operations before the wallet will stop signing transactions.
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer walletRemainingSignatures = null;
    
    /**
     * Total number of signed single hashes returned by the card in
     * [SignCommand] responses since card personalization.
     * Sums up array elements within all [SignCommand].
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer walletSignedHashes = null;
    
    /**
     * Any non-zero value indicates that the card experiences some hardware problems.
     * User should withdraw the value to other blockchain wallet as soon as possible.
     * Non-zero Health tag will also appear in responses of all other commands.
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer health = null;
    
    /**
     * Whether the card requires issuer’s confirmation of activation.
     * is "true" if the card requires activation,
     * is 'false" if the card is activated or does not require activation
     */
    private final boolean isActivated = false;
    
    /**
     * A random challenge generated by personalisation that should be signed and returned
     * to COS by the issuer to confirm the card has been activated.
     * This field will not be returned if the card is activated.
     */
    @org.jetbrains.annotations.Nullable()
    private final byte[] activationSeed = null;
    
    /**
     * Returned only if [SigningMethod.SignPos] enabling POS transactions is supported by card.
     */
    @org.jetbrains.annotations.Nullable()
    private final byte[] paymentFlowVersion = null;
    
    /**
     * This value can be initialized by terminal and will be increased by COS on execution of every [SignCommand].
     * For example, this field can store blockchain “nonce” for quick one-touch transaction on POS terminals.
     * Returned only if [SigningMethod.SignPos]  enabling POS transactions is supported by card.
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer userCounter = null;
    
    /**
     * This value can be initialized by App (with PIN2 confirmation) and will be increased by COS
     * with the execution of each [SignCommand]. For example, this field can store blockchain “nonce”
     * for a quick one-touch transaction on POS terminals. Returned only if [SigningMethod.SignPos].
     */
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer userProtectedCounter = null;
    
    /**
     * When this value is true, it means that the application is linked to the card,
     * and COS will not enforce security delay if [SignCommand] will be called
     * with [TlvTag.TerminalTransactionSignature] parameter containing a correct signature of raw data
     * to be signed made with [TlvTag.TerminalPublicKey].
     */
    private final boolean terminalIsLinked = false;
    
    /**
     * Detailed information about card contents. Format is defined by the card issuer.
     * Cards complaint with Tangem Wallet application should have TLV format.
     */
    @org.jetbrains.annotations.Nullable()
    private final com.tangem.commands.CardData cardData = null;
    
    /**
     * Unique Tangem card ID number.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCardId() {
        return null;
    }
    
    /**
     * Name of Tangem card manufacturer.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getManufacturerName() {
        return null;
    }
    
    /**
     * Current status of the card.
     */
    @org.jetbrains.annotations.Nullable()
    public final com.tangem.commands.CardStatus getStatus() {
        return null;
    }
    
    /**
     * Version of Tangem COS.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFirmwareVersion() {
        return null;
    }
    
    /**
     * Public key that is used to authenticate the card against manufacturer’s database.
     * It is generated one time during card manufacturing.
     */
    @org.jetbrains.annotations.Nullable()
    public final byte[] getCardPublicKey() {
        return null;
    }
    
    /**
     * Card settings defined by personalization (bit mask: 0 – Enabled, 1 – Disabled).
     */
    @org.jetbrains.annotations.Nullable()
    public final com.tangem.commands.SettingsMask getSettingsMask() {
        return null;
    }
    
    /**
     * Public key that is used by the card issuer to sign IssuerData field.
     */
    @org.jetbrains.annotations.Nullable()
    public final byte[] getIssuerPublicKey() {
        return null;
    }
    
    /**
     * Explicit text name of the elliptic curve used for all wallet key operations.
     * Supported curves: ‘secp256k1’ and ‘ed25519’.
     */
    @org.jetbrains.annotations.Nullable()
    public final com.tangem.commands.EllipticCurve getCurve() {
        return null;
    }
    
    /**
     * Total number of signatures allowed for the wallet when the card was personalized.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMaxSignatures() {
        return null;
    }
    
    /**
     * Defines what data should be submitted to SIGN command.
     */
    @org.jetbrains.annotations.Nullable()
    public final com.tangem.commands.SigningMethodMask getSigningMethods() {
        return null;
    }
    
    /**
     * Delay in seconds before COS executes commands protected by PIN2.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getPauseBeforePin2() {
        return null;
    }
    
    /**
     * Public key of the blockchain wallet.
     */
    @org.jetbrains.annotations.Nullable()
    public final byte[] getWalletPublicKey() {
        return null;
    }
    
    /**
     * Remaining number of [SignCommand] operations before the wallet will stop signing transactions.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getWalletRemainingSignatures() {
        return null;
    }
    
    /**
     * Total number of signed single hashes returned by the card in
     * [SignCommand] responses since card personalization.
     * Sums up array elements within all [SignCommand].
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getWalletSignedHashes() {
        return null;
    }
    
    /**
     * Any non-zero value indicates that the card experiences some hardware problems.
     * User should withdraw the value to other blockchain wallet as soon as possible.
     * Non-zero Health tag will also appear in responses of all other commands.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getHealth() {
        return null;
    }
    
    /**
     * Whether the card requires issuer’s confirmation of activation.
     * is "true" if the card requires activation,
     * is 'false" if the card is activated or does not require activation
     */
    public final boolean isActivated() {
        return false;
    }
    
    /**
     * A random challenge generated by personalisation that should be signed and returned
     * to COS by the issuer to confirm the card has been activated.
     * This field will not be returned if the card is activated.
     */
    @org.jetbrains.annotations.Nullable()
    public final byte[] getActivationSeed() {
        return null;
    }
    
    /**
     * Returned only if [SigningMethod.SignPos] enabling POS transactions is supported by card.
     */
    @org.jetbrains.annotations.Nullable()
    public final byte[] getPaymentFlowVersion() {
        return null;
    }
    
    /**
     * This value can be initialized by terminal and will be increased by COS on execution of every [SignCommand].
     * For example, this field can store blockchain “nonce” for quick one-touch transaction on POS terminals.
     * Returned only if [SigningMethod.SignPos]  enabling POS transactions is supported by card.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getUserCounter() {
        return null;
    }
    
    /**
     * This value can be initialized by App (with PIN2 confirmation) and will be increased by COS
     * with the execution of each [SignCommand]. For example, this field can store blockchain “nonce”
     * for a quick one-touch transaction on POS terminals. Returned only if [SigningMethod.SignPos].
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getUserProtectedCounter() {
        return null;
    }
    
    /**
     * When this value is true, it means that the application is linked to the card,
     * and COS will not enforce security delay if [SignCommand] will be called
     * with [TlvTag.TerminalTransactionSignature] parameter containing a correct signature of raw data
     * to be signed made with [TlvTag.TerminalPublicKey].
     */
    public final boolean getTerminalIsLinked() {
        return false;
    }
    
    /**
     * Detailed information about card contents. Format is defined by the card issuer.
     * Cards complaint with Tangem Wallet application should have TLV format.
     */
    @org.jetbrains.annotations.Nullable()
    public final com.tangem.commands.CardData getCardData() {
        return null;
    }
    
    public Card(@org.jetbrains.annotations.NotNull()
    java.lang.String cardId, @org.jetbrains.annotations.NotNull()
    java.lang.String manufacturerName, @org.jetbrains.annotations.Nullable()
    com.tangem.commands.CardStatus status, @org.jetbrains.annotations.Nullable()
    java.lang.String firmwareVersion, @org.jetbrains.annotations.Nullable()
    byte[] cardPublicKey, @org.jetbrains.annotations.Nullable()
    com.tangem.commands.SettingsMask settingsMask, @org.jetbrains.annotations.Nullable()
    byte[] issuerPublicKey, @org.jetbrains.annotations.Nullable()
    com.tangem.commands.EllipticCurve curve, @org.jetbrains.annotations.Nullable()
    java.lang.Integer maxSignatures, @org.jetbrains.annotations.Nullable()
    com.tangem.commands.SigningMethodMask signingMethods, @org.jetbrains.annotations.Nullable()
    java.lang.Integer pauseBeforePin2, @org.jetbrains.annotations.Nullable()
    byte[] walletPublicKey, @org.jetbrains.annotations.Nullable()
    java.lang.Integer walletRemainingSignatures, @org.jetbrains.annotations.Nullable()
    java.lang.Integer walletSignedHashes, @org.jetbrains.annotations.Nullable()
    java.lang.Integer health, boolean isActivated, @org.jetbrains.annotations.Nullable()
    byte[] activationSeed, @org.jetbrains.annotations.Nullable()
    byte[] paymentFlowVersion, @org.jetbrains.annotations.Nullable()
    java.lang.Integer userCounter, @org.jetbrains.annotations.Nullable()
    java.lang.Integer userProtectedCounter, boolean terminalIsLinked, @org.jetbrains.annotations.Nullable()
    com.tangem.commands.CardData cardData) {
        super();
    }
}