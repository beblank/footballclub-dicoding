package com.adit.footballclub.Utils

import java.text.SimpleDateFormat

class Utils{
    companion object {
        fun dateStringConverter(s:String):String{
            val oldFormat = SimpleDateFormat("dd/MM/yyyy")
            val nf = oldFormat.parse(s)
            val newFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
            return newFormat.format(nf)
        }
    }
}