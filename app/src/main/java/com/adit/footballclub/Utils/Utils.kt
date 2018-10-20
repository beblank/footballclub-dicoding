package com.adit.footballclub.Utils

import java.text.SimpleDateFormat

class Utils{
    companion object {
        fun dateStringConverter(s:String?):String{
            var result = ""
            if (s != null){
                val oldFormat = SimpleDateFormat("dd/MM/yyyy")
                val nf = oldFormat.parse(s)
                val newFormat = SimpleDateFormat("EEEE, dd MMM yyyy")
                result = newFormat.format(nf)
            }
            return result
        }
    }
}