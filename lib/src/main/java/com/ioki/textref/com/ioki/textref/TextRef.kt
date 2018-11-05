package com.ioki.textref.com.ioki.textref

import android.content.Context
import androidx.annotation.StringRes
import java.util.Arrays

class TextRef
private constructor(
    internal val string: String?,
    internal val id: Int?,
    internal vararg val args: Any
) {

    constructor(string: String, vararg args: Any) : this(string, null, *args)

    constructor(@StringRes id: Int, vararg args: Any) : this(null, id, *args)

    fun resolve(context: Context): String {
        return when {
            string != null && args.isEmpty() -> string
            string != null -> string.format(*args)
            args.isEmpty() -> context.getString(id!!)
            else -> context.getString(id!!, *args)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TextRef

        if (string != other.string) return false
        if (id != other.id) return false
        if (!Arrays.equals(args, other.args)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = string?.hashCode() ?: 0
        result = 31 * result + (id ?: 0)
        result = 31 * result + Arrays.hashCode(args)
        return result
    }

    override fun toString(): String {
        val textString = string?.let { "string=$it" } ?: "id=$id"
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
