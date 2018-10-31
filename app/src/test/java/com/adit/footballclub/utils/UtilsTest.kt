package com.adit.footballclub.utils

import org.junit.Test
import kotlin.test.assertEquals

class UtilsTest{
        @Test
        fun testToDateString(){
            val date = "28/02/2018"
            assertEquals("Wed, 28 Feb 2018", Utils.dateStringConverter(date))
        }

}