package com.ioki.textref

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.artemzin.assert_parcelable.AssertParcelable.assertThatObjectParcelable
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextRefParcelableTest {

    companion object {
        private val argsList = listOf(
            emptyArray(),
            arrayOf(1, "foo", true, 5L, 3.3, listOf("foo", "bar"), mapOf(1 to "foo", 2 to "bar"), List::class.java),
            arrayOf(TextRef("foo", TextRef("bar")))
        )
    }

    @Test
    fun parcelStringRes() {
        argsList.forEach { args ->
            assertThatObjectParcelable(TextRef(1, *args))
        }
    }

    @Test
    fun parcelString() {
        argsList.forEach { args ->
            assertThatObjectParcelable(TextRef("foobar", *args))
        }
    }

    @Test(expected = RuntimeException::class)
    fun parcelStringResWithNonParcelable() {
        assertThatObjectParcelable(TextRef(1, Unit))
    }

    @Test(expected = RuntimeException::class)
    fun parcelStringWithNonParcelable() {
        assertThatObjectParcelable(TextRef("foobar", Unit))
    }
}
