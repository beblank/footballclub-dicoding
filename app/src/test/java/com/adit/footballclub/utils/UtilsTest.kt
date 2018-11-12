package com.adit.footballclub.utils

import org.junit.Test
import kotlin.test.assertEquals

class UtilsTest{
    @Test
    fun testToDateString(){
        val date = "2018-11-10"
        val time = "17:30:00+00:00"
        assertEquals("Min, 11 Nov 2018", Utils.dateStringConverter(date, time))
    }

    @Test
    fun testDateTimeGMT(){
        val date = "2018-11-10"
        val time = "17:30:00+00:00"
        assertEquals("00:30:00", Utils.timeStringConverter(date, time))
    }

    @Test
    fun testDateTime(){
        val date = "2018-11-10"
        val time = "17:30:00+00:00"
        val s = "$date $time"
        val pattern = "yyyy-MM-dd HH:mm:ss"
        assertEquals("2018-11-11 00:30:00", Utils.dateTimeParser(s, pattern, pattern))
    }

}