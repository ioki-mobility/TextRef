package com.ioki.textref

import android.content.Context
import android.os.Parcel
import android.os.ParcelFormatException
import android.os.Parcelable
import androidx.annotation.StringRes
import java.util.Arrays

/**
 * An abstraction of text that holds either a [String] object or a [StringRes] ID.
 *
 * Use [resolve] to get a [String] out of it.
 */
class TextRef
private constructor(
    internal val value: Any,
    internal vararg val args: Any
) : Parcelable {

    /**
     * Creates a new TextRef from a [String]. Supports formatting using [java.util.Formatter].
     *
     * @param string The string used to create the TextRef
     * @param args Format args used to format the string
     */
    constructor(string: String, vararg args: Any) : this(string as Any, *args)

    /**
     * Creates a new TextRef from an Android [StringRes] ID. Supports formatting using [java.util.Formatter].
     *
     * @param id The String resource ID used to create the TextRef
     * @param args Format args used to format the string
     */
    constructor(@StringRes id: Int, vararg args: Any) : this(id as Any, *args)

    /**
     * Resolves the contents of the TextRef to a [String].
     *
     * Any format args passed on creation will be used to format the string
     *
     * @param context The context used to resolve the string if created from a [StringRes] ID
     * @return A String, formatted with any args passed on creation
     */
    fun resolve(context: Context): String {
        val args = processArgs(context)
        return when {
            value is String && args.isEmpty() -> value
            value is String -> value.format(*args)
            args.isEmpty() -> context.getString(value as Int)
            else -> context.getString(value as Int, *args)
        }
    }

    private fun processArgs(context: Context): Array<Any> =
        args.map {
            if (it is TextRef) it.resolve(context)
            else it
        }.toTypedArray()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TextRef

        if (value != other.value) return false
        if (!Arrays.equals(args, other.args)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + Arrays.hashCode(args)
        return result
    }

    override fun toString(): String {
        val textString = when (value) {
            is String -> "string=$value"
            else -> "id=$value"
        }
        val argString = if (args.isEmpty())
            ""
        else
            args.joinToString(prefix = ", args=[", postfix = "]")
        return "$textString$argString"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        when (value) {
            is Int -> {
                parcel.writeInt(TYPE_STRING_RES)
                parcel.writeInt(value)
            }
            is String -> {
                parcel.writeInt(TYPE_STRING)
                parcel.writeString(value)
            }
            else -> throw ParcelFormatException("Unsupported type class: ${value::class.java} for value: $value")
        }
        parcel.writeArray(args)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<TextRef> {
        /**
         * A TextRef holding an empty [String]
         */
        @JvmField
        val EMPTY = TextRef("")

        override fun createFromParcel(parcel: Parcel): TextRef {
            val type = parcel.readInt()
            return when (type) {
                TYPE_STRING_RES -> {
                    val resId = parcel.readInt()
                    val args = parcel.readArray(this::class.java.classLoader)!!
                    TextRef(resId, *args)
                }
                TYPE_STRING -> {
                    val string = parcel.readString()!!
                    val args = parcel.readArray(this::class.java.classLoader)!!
                    TextRef(string, *args)
                }
                else -> throw ParcelFormatException("Unsupported type id: $type")
            }
        }

        override fun newArray(size: Int): Array<TextRef?> {
            return arrayOfNulls(size)
        }

        private const val TYPE_STRING_RES = 1
        private const val TYPE_STRING = 2
    }
}
