package com.ioki.textref

import android.content.Context
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
) {

    /**
     * Resolves the contents of the TextRef to a [String].
     *
     * Any format args passed on creation will be used to format the string
     *
     * @param context The context used to resolve the string if created from a [StringRes] ID
     * @return A String, formatted with any args passed on creation
     */
    fun resolve(context: Context): String =
        when {
            value is String && args.isEmpty() -> value
            value is String -> value.format(*args)
            args.isEmpty() -> context.getString(value as Int)
            else -> context.getString(value as Int, *args)
        }

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

    companion object {
        /**
         * A TextRef holding an empty [String]
         */
        @JvmField
        val EMPTY = TextRef("")

        /**
         * Creates a new TextRef from a [String]. Supports formatting using [java.util.Formatter].
         *
         * @param string The string used to create the TextRef
         * @param args Format args used to format the string
         */
        @JvmStatic
        fun of(string: String, vararg args: Any): TextRef = TextRef(string as Any, *args)

        /**
         * Creates a new TextRef from an Android [StringRes] ID. Supports formatting using [java.util.Formatter].
         *
         * @param id The String resource ID used to create the TextRef
         * @param args Format args used to format the string
         */
        @JvmStatic
        fun of(@StringRes id: Int, vararg args: Any): TextRef = TextRef(id as Any, *args)
    }
}
