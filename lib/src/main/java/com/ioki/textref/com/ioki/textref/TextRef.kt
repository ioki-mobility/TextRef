package com.ioki.textref.com.ioki.textref

import android.content.Context
import androidx.annotation.StringRes
import java.util.Arrays

class TextRef
private constructor(
    internal val value: Any,
    internal vararg val args: Any
) {

    constructor(string: String, vararg args: Any) : this(string as Any, *args)

    constructor(@StringRes id: Int, vararg args: Any) : this(id as Any, *args)

    fun resolve(context: Context): String {
        return when {
            value is String && args.isEmpty() -> value
            value is String -> value.format(*args)
            args.isEmpty() -> context.getString(value as Int)
            else -> context.getString(value as Int, *args)
        }
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
        val argString =
            if (args.isEmpty())
                ""
            else
                args.joinToString(prefix = ", args=[", postfix = "]")
        return "$textString$argString"
    }

    companion object {
        @JvmField
        val EMPTY = TextRef("")
    }
}
