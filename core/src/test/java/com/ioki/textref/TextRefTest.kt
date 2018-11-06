package com.ioki.textref

import android.content.Context
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

    private val formattedStringWithIntArg = "foo $intArg"
    private val formattedStringWithStringArg = "foo bar"
    private val formattedStringWithTwoArgs = "foo $intArg bar"

    private val formattedIdWithoutArg = "biz"
    private val formattedIdWithIntArg = "biz $intArg"
    private val formattedIdWithStringArg = "biz bar"
    private val formattedIdWithTwoArgs = "biz $intArg bar"

    @Before
    fun setUp() {
        `when`(mockContext.getString(idWithoutArg)).thenReturn(formattedIdWithoutArg)
        `when`(mockContext.getString(idWithIntArg, intArg)).thenReturn(formattedIdWithIntArg)
        `when`(mockContext.getString(idWithStringArg, stringArg)).thenReturn(formattedIdWithStringArg)
        `when`(mockContext.getString(idWithTwoArgs, intArg, stringArg)).thenReturn(formattedIdWithTwoArgs)
    }

    @Test
    fun resolve_createdWithString_resultIsCorrect() {
        val result = TextRef.of(stringWithoutArg).resolve(mockContext)

        assertThat(result).isEqualTo(stringWithoutArg)
    }

    @Test
    fun resolve_createdWithId_resultIsCorrect() {
        val result = TextRef.of(idWithoutArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedIdWithoutArg)
    }

    @Test
    fun resolve_createdWithStringAndIntArg_resultIsCorrect() {
        val result = TextRef.of(stringWithIntArg, intArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedStringWithIntArg)
    }

    @Test
    fun resolve_createdWithStringAndStringArg_resultIsCorrect() {
        val result = TextRef.of(stringWithStringArg, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedStringWithStringArg)
    }

    @Test
    fun resolve_createdWithStringAndTwoArgs_resultIsCorrect() {
        val result = TextRef.of(stringWithTwoArgs, intArg, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedStringWithTwoArgs)
    }

    @Test
    fun resolve_createdWithIdAndIntArg_resultIsCorrect() {
        val result = TextRef.of(idWithIntArg, intArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedIdWithIntArg)
    }

    @Test
    fun resolve_createdWithIdAndStringArg_resultIsCorrect() {
        val result = TextRef.of(idWithStringArg, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedIdWithStringArg)
    }

    @Test
    fun resolve_createdWithIdAndTwoArgs_resultIsCorrect() {
        val result = TextRef.of(idWithTwoArgs, intArg, stringArg).resolve(mockContext)

        assertThat(result).isEqualTo(formattedIdWithTwoArgs)
    }

    @Test
    fun toString_createdWithId_resultIsCorrect() {
        val result = TextRef.of(idWithoutArg).toString()

        assertThat(result).isEqualTo("id=$idWithoutArg")
    }

    @Test
    fun toString_createdWithIdAndTwoArgs_resultIsCorrect() {
        val result = TextRef.of(idWithTwoArgs, intArg, stringArg).toString()

        assertThat(result).isEqualTo("id=$idWithTwoArgs, args=[$intArg, $stringArg]")
    }

    @Test
    fun toString_createdWithString_resultIsCorrect() {
        val result = TextRef.of(stringWithoutArg).toString()

        assertThat(result).isEqualTo("string=$stringWithoutArg")
    }

    @Test
    fun toString_createdWithStringAndTwoArgs_resultIsCorrect() {
        val result = TextRef.of(stringWithTwoArgs, intArg, stringArg).toString()

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
        assertThat(TextRef.of(5))
            .isEqualTo(TextRef.of(5))
        assertThat(TextRef.of(6))
            .isNotEqualTo(TextRef.of(7))
        assertThat(TextRef.of("foobar"))
            .isEqualTo(TextRef.of("foobar"))
        assertThat(TextRef.of("foobar"))
            .isNotEqualTo(TextRef.of("bizbaz"))
        assertThat(TextRef.of("foobar"))
            .isNotEqualTo(TextRef.of(7))
        assertThat(TextRef.of("foobar", 1))
            .isEqualTo(TextRef.of("foobar", 1))
        assertThat(TextRef.of("foobar", 1))
            .isNotEqualTo(TextRef.of("bizbaz", 2))
        assertThat(TextRef.of(5, 1))
            .isEqualTo(TextRef.of(5, 1))
        assertThat(TextRef.of(5, 1))
            .isNotEqualTo(TextRef.of(5, 2))
    }

    @Test
    fun testHashCode() {
        assertThat(TextRef.of(5)
            .hashCode()).isEqualTo(TextRef.of(5).hashCode())
        assertThat(TextRef.of(6)
            .hashCode()).isNotEqualTo(TextRef.of(7).hashCode())
        assertThat(TextRef.of("foobar").hashCode())
            .isEqualTo(TextRef.of("foobar").hashCode())
        assertThat(TextRef.of("foobar").hashCode())
            .isNotEqualTo(TextRef.of("bizbaz").hashCode())
        assertThat(TextRef.of("foobar").hashCode())
            .isNotEqualTo(TextRef.of(7).hashCode())
        assertThat(TextRef.of("foobar", 1).hashCode())
            .isEqualTo(TextRef.of("foobar", 1).hashCode())
        assertThat(TextRef.of("foobar", 1).hashCode())
            .isNotEqualTo(TextRef.of("bizbaz", 2).hashCode())
        assertThat(TextRef.of(5, 1).hashCode())
            .isEqualTo(TextRef.of(5, 1).hashCode())
        assertThat(TextRef.of(5, 1).hashCode())
            .isNotEqualTo(TextRef.of(5, 2).hashCode())
    }
}
