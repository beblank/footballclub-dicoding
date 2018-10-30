package com.adit.footballclub.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils{
    companion object {
        fun dateStringConverter(s:String?):String{
            var result = ""
            if (s != null){
                val oldFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                val nf = oldFormat.parse(s)
                val newFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US)
                result = newFormat.format(nf)
            }
            return result
        }
    }
}