package com.ioki.textref

import android.content.Context
import android.content.res.Resources
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TextRefTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockResources: Resources

    private val quantity = 7

    private val stringWithoutArg = "foo"
    private val stringWithIntArg = "foo %d"
    private val stringWithStringArg = "foo %s"
    private val stringWithTwoArgs = "foo %d %s"

    private val idWithoutArg = 1
    private val idWithIntArg = 2
    private val idWithStringArg = 3
    private val idWithTwoArgs = 4

    private val intArg = 5
    private val stringArg = "bar"
    private val textRefArg = TextRef.string(stringArg)

    private val formattedStringWithIntArg = "foo $intArg"
    private val formattedStringWithStringArg = "foo bar"
    private val formattedStringWithTwoArgs = "foo $intArg bar"

    private val formattedIdWithoutArg = "biz"
    private val formattedIdWithIntArg = "biz $intArg"
    private val formattedIdWithStringArg = "biz bar"
    private val formattedIdWithTwoArgs = "biz $intArg bar"

    private val formattedPluralWithoutArg = "boz"
    private val formattedPluralWithIntArg = "boz $intArg"
    private val formattedPluralWithStringArg = "boz bar"
    private val formattedPluralWithTwoArgs = "boz $intArg bar"

    @Before
    fun setUp() {
        `when`(mockContext.resources).thenReturn(mockResources)
        `when`(mockContext.getString(idWithoutArg)).thenReturn(formattedIdWithoutArg)
        `when`(mockContext.getString(idWithIntArg, intArg)).thenReturn(formattedIdWithIntArg)
        `when`(mockContext.getString(idWithStringArg, stringArg)).thenReturn(formattedIdWithStringArg)
        `when`(mockContext.getString(idWithTwoArgs, intArg, stringArg)).thenReturn(formattedIdWithTwoArgs)
        `when`(mockResources.getQuantityString(idWithoutArg, quantity)).thenReturn(formattedPluralWithoutArg)
        `when`(mockResources.getQuantityString(idWithIntArg, quantity, intArg)).thenReturn(formattedPluralWithIntArg)
        `when`(mockResources.getQuantityString(idWithStringArg, quantity, stringArg)).thenReturn(
            formattedPluralWithStringArg
        )
        `when`(mockResources.getQuantityString(idWithTwoArgs, quantity, intArg, stringArg)).thenReturn(
            formattedPluralWithTwoArgs
        )
    }

    @Test
    fun resolve_createdWithString_resultIsCorrect() {
        val result = TextRef.string(stringWithoutArg).resolve(mockContext)

        assertThat(result).isEqualTo(stringWithoutArg)
    }

    @Test
    fun resolve_createdWithStringRes_resultIsCorrect() {
        val result = TextRef.stringRes(idWithoutArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedIdWithoutArg)
    }

    @Test
    fun resolve_createdWithPluralsRes_resultIsCorrect() {
        val result = TextRef.pluralsRes(idWithoutArg, quantity).resolve(mockContext)

        assertThat(result).isEqualTo(formattedPluralWithoutArg)
    }

    @Test
    fun resolve_createdWithStringAndIntArg_resultIsCorrect() {
        val result = TextRef.string(stringWithIntArg, intArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedStringWithIntArg)
    }

    @Test
    fun resolve_createdWithStringAndStringArg_resultIsCorrect() {
        val result = TextRef.string(stringWithStringArg, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedStringWithStringArg)
    }

    @Test
    fun resolve_createdWithStringAndTextRefArg_resultIsCorrect() {
        val result = TextRef.string(stringWithStringArg, textRefArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedStringWithStringArg)
    }

    @Test
    fun resolve_createdWithStringAndTwoArgs_resultIsCorrect() {
        val result = TextRef.string(stringWithTwoArgs, intArg, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedStringWithTwoArgs)
    }

    @Test
    fun resolve_createdWithStringResAndIntArg_resultIsCorrect() {
        val result = TextRef.stringRes(idWithIntArg, intArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedIdWithIntArg)
    }

    @Test
    fun resolve_createdWithStringResAndStringArg_resultIsCorrect() {
        val result = TextRef.stringRes(idWithStringArg, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedIdWithStringArg)
    }

    @Test
    fun resolve_createdWithStringResAndTextRefArg_resultIsCorrect() {
        val result = TextRef.stringRes(idWithStringArg, textRefArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedIdWithStringArg)
    }

    @Test
    fun resolve_createdWithStringResAndTwoArgs_resultIsCorrect() {
        val result = TextRef.stringRes(idWithTwoArgs, intArg, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedIdWithTwoArgs)
    }

    @Test
    fun toString_createdWithStringRes_resultIsCorrect() {
        val result = TextRef.stringRes(idWithoutArg).toString()

        assertThat(result).isEqualTo("id=$idWithoutArg")
    }

    @Test
    fun toString_createdWithStringResAndTwoArgs_resultIsCorrect() {
        val result = TextRef.stringRes(idWithTwoArgs, intArg, stringArg).toString()

        assertThat(result).isEqualTo("id=$idWithTwoArgs, args=[$intArg, $stringArg]")
    }

    @Test
    fun resolve_createdWithPluralResAndIntArg_resultIsCorrect() {
        val result = TextRef.pluralsRes(idWithIntArg, quantity, intArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedPluralWithIntArg)
    }

    @Test
    fun resolve_createdWithPluralResAndStringArg_resultIsCorrect() {
        val result = TextRef.pluralsRes(idWithStringArg, quantity, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedPluralWithStringArg)
    }

    @Test
    fun resolve_createdWithPluralResAndTextRefArg_resultIsCorrect() {
        val result = TextRef.pluralsRes(idWithStringArg, quantity, textRefArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedPluralWithStringArg)
    }

    @Test
    fun resolve_createdWithPluralResAndTwoArgs_resultIsCorrect() {
        val result = TextRef.pluralsRes(idWithTwoArgs, quantity, intArg, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedPluralWithTwoArgs)
    }

    @Test
    fun toString_createdWithPluralRes_resultIsCorrect() {
        val result = TextRef.pluralsRes(idWithoutArg, quantity).toString()

        assertThat(result).isEqualTo("id=$idWithoutArg, quantity=$quantity")
    }

    @Test
    fun toString_createdWithPluralResAndTwoArgs_resultIsCorrect() {
        val result = TextRef.pluralsRes(idWithTwoArgs, quantity, intArg, stringArg).toString()

        assertThat(result).isEqualTo("id=$idWithTwoArgs, quantity=$quantity, args=[$intArg, $stringArg]")
    }

    @Test
    fun toString_createdWithString_resultIsCorrect() {
        val result = TextRef.string(stringWithoutArg).toString()

        assertThat(result).isEqualTo("string=$stringWithoutArg")
    }

    @Test
    fun toString_createdWithStringAndTwoArgs_resultIsCorrect() {
        val result = TextRef.string(stringWithTwoArgs, intArg, stringArg).toString()

        assertThat(result).isEqualTo("string=$stringWithTwoArgs, args=[$intArg, $stringArg]")
    }

    @Test
    fun empty_resolvesToEmptyString() {
        val result = TextRef.EMPTY

        assertThat(result.resolve(mockContext)).isEqualTo("")
    }

    @Test
    fun empty_isSingleton() {
        val result1 = TextRef.EMPTY
        val result2 = TextRef.EMPTY

        assertThat(result1).isSameAs(result2)
    }

    @Test
    fun testEquals() {
        assertThat(TextRef.stringRes(5))
            .isEqualTo(TextRef.stringRes(5))
        assertThat(TextRef.stringRes(6))
            .isNotEqualTo(TextRef.stringRes(7))
        assertThat(TextRef.string("foobar"))
            .isEqualTo(TextRef.string("foobar"))
        assertThat(TextRef.string("foobar"))
            .isNotEqualTo(TextRef.string("bizbaz"))
        assertThat(TextRef.string("foobar"))
            .isNotEqualTo(TextRef.stringRes(7))
        assertThat(TextRef.string("foobar", 1))
            .isEqualTo(TextRef.string("foobar", 1))
        assertThat(TextRef.string("foobar", 1))
            .isNotEqualTo(TextRef.string("bizbaz", 2))
        assertThat(TextRef.stringRes(5, 1))
            .isEqualTo(TextRef.stringRes(5, 1))
        assertThat(TextRef.stringRes(5, 1))
            .isNotEqualTo(TextRef.stringRes(5, 2))
    }

    @Test
    fun testHashCode() {
        assertThat(
            TextRef.stringRes(5)
                .hashCode()
        ).isEqualTo(TextRef.stringRes(5).hashCode())
        assertThat(
            TextRef.stringRes(6)
                .hashCode()
        ).isNotEqualTo(TextRef.stringRes(7).hashCode())
        assertThat(TextRef.string("foobar").hashCode())
            .isEqualTo(TextRef.string("foobar").hashCode())
        assertThat(TextRef.string("foobar").hashCode())
            .isNotEqualTo(TextRef.string("bizbaz").hashCode())
        assertThat(TextRef.string("foobar").hashCode())
            .isNotEqualTo(TextRef.stringRes(7).hashCode())
        assertThat(TextRef.string("foobar", 1).hashCode())
            .isEqualTo(TextRef.string("foobar", 1).hashCode())
        assertThat(TextRef.string("foobar", 1).hashCode())
            .isNotEqualTo(TextRef.string("bizbaz", 2).hashCode())
        assertThat(TextRef.stringRes(5, 1).hashCode())
            .isEqualTo(TextRef.stringRes(5, 1).hashCode())
        assertThat(TextRef.stringRes(5, 1).hashCode())
            .isNotEqualTo(TextRef.stringRes(5, 2).hashCode())
    }
}
