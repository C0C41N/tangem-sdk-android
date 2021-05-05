package com.tangem.commands.common.jsonConverter

import com.squareup.moshi.*
import com.tangem.commands.CommandResponse
import com.tangem.commands.common.card.FirmwareVersion
import com.tangem.commands.common.card.masks.ProductMask
import com.tangem.commands.common.card.masks.SettingsMask
import com.tangem.commands.common.card.masks.SigningMethodMask
import com.tangem.commands.common.card.masks.WalletSettingsMask
import com.tangem.common.extensions.hexToBytes
import com.tangem.common.extensions.toHexString
import java.text.DateFormat
import java.util.*

/**
 * Created by Anton Zhilenkov on 30/04/2021.
 */
class MoshiJsonConverter(adapters: List<Any>) {

    val moshi: Moshi = Moshi.Builder().apply { adapters.forEach { this.add(it) } }.build()
    val mapAdapter: JsonAdapter<Map<String, Any>>
    val anyAdapter: JsonAdapter<Any>

    init {
        val mapType = Types.newParameterizedType(MutableMap::class.java, String::class.java, Any::class.java)
        mapAdapter = moshi.adapter(mapType)
        anyAdapter = moshi.adapter(Any::class.java)
    }

    inline fun <reified T> fromJson(json: String): T? {
        return moshi.adapter(T::class.java).fromJson(json)
    }

    fun toJson(any: Any?, indent: String = "   "): String {
        return when (any) {
            null -> ""
            is CommandResponse -> moshi.adapter<CommandResponse>(any::class.java).indent(indent).toJson(any)
            else -> anyAdapter.toJson(any)
        }
    }

    fun toMap(any: Any?): Map<String, Any> {
        val any = any ?: return mapOf()
        return mapAdapter.fromJson(toJson(any)) ?: mapOf()
    }

    companion object {
        fun tangemSdkJsonConverter(): MoshiJsonConverter {
            return MoshiJsonConverter(getTangemSdkAdapters())
        }

        fun getTangemSdkAdapters(): List<Any> {
            return listOf(
                TangemSdkAdapter.ByteTypeAdapter(),
                TangemSdkAdapter.SigningMethodTypeAdapter(),
                TangemSdkAdapter.SettingsMaskTypeAdapter(),
                TangemSdkAdapter.ProductMaskTypeAdapter(),
                TangemSdkAdapter.WalletSettingsMaskAdapter(),
                TangemSdkAdapter.DateTypeAdapter(),
                TangemSdkAdapter.FirmwareVersionAdapter(),
            )
        }
    }
}

class TangemSdkAdapter {
    class ByteTypeAdapter {
        @ToJson
        fun toJson(src: ByteArray): String = src.toHexString()

        @FromJson
        fun fromJson(json: String): ByteArray = json.hexToBytes()
    }

    class SettingsMaskTypeAdapter {
        @ToJson
        fun toJson(src: SettingsMask): String = src.toString()

        @FromJson
        fun fromJson(json: String): SettingsMask = SettingsMask.fromString(json)
    }

    class ProductMaskTypeAdapter {
        @ToJson
        fun toJson(src: ProductMask): String = src.toString()

        @FromJson
        fun fromJson(json: String): ProductMask = ProductMask.fromString(json)
    }

    class SigningMethodTypeAdapter {
        @ToJson
        fun toJson(src: SigningMethodMask): String = src.toString()

        @FromJson
        fun fromJson(json: String): SigningMethodMask = SigningMethodMask.fromString(json)
    }

    class WalletSettingsMaskAdapter {
        @ToJson
        fun toJson(src: WalletSettingsMask): String = src.toString()

        @FromJson
        fun fromJson(json: String): WalletSettingsMask = WalletSettingsMask.fromString(json)
    }

    class DateTypeAdapter {
        private val dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale("en_US"))

        @ToJson
        fun toJson(src: Date): String {
            return dateFormatter.format(src).toString()
        }

        @FromJson
        fun fromJson(json: String): Date = dateFormatter.parse(json)
    }

    class FirmwareVersionAdapter {
        @ToJson
        fun toJson(src: FirmwareVersion): String = src.version

        @FromJson
        fun fromJson(json: String): FirmwareVersion = FirmwareVersion(json)
    }
}
