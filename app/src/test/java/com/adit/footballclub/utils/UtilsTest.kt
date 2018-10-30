package com.adit.footballclub.utils

import org.junit.Assert
import org.junit.Test

class UtilsTest{
        @Test
        fun testToDateString(){
            val date = "28/02/2018"
            Assert.assertEquals("Wed, 28 Feb 2018", Utils.dateStringConverter(date))
        }

}