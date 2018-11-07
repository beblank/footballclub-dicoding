package com.adit.footballclub.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Utils{
    companion object {
        fun dateStringConverter(date:String?, time:String?):String{
            var result = ""
            val s = "$date $time"
            if (date != null && time != null){
                result = dateTimeParser(s, "yyyy-MM-dd HH:mm:ss", "EEE, dd MMM yyyy")
            }
            return result
        }

        fun timeStringConverter(date:String?, time:String?):String{
            var result = ""
            val s = "$date $time"
            if (date != null && time != null){
                result = dateTimeParser(s, "yyyy-MM-dd HH:mm:ss", "HH:mm:ss")
            }
            return result
        }

        fun dateTimeParser(s:String, oldPattern:String, newPattern:String):String{
            val oldFormat = SimpleDateFormat(oldPattern, Locale.US)
            val utc = TimeZone.getTimeZone("UTC")
            oldFormat.timeZone = utc
            val nf = oldFormat.parse(s)
            val wib = TimeZone.getTimeZone("Asia/Jakarta")
            val newFormat = SimpleDateFormat(newPattern, Locale("id"))
            newFormat.timeZone = wib
            return newFormat.format(nf)
        }
    }
}