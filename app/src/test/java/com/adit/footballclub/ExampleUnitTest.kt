package com.adit.footballclub

import org.junit.Test

import org.junit.Assert.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testDecimalFormat(){
        val symbols = DecimalFormatSymbols(Locale("id"))
        val df = DecimalFormat("#,###.00", symbols)
        val d1 = 1003.54213
        val result = df.format(d1)
        assertEquals("", result)
    }
}
