package com.myfitness.util

import java.text.SimpleDateFormat
import java.util.*

class MyFitnessUtil {

    companion object{

        fun formatDate(strDate : String) : String{

            var format1 = SimpleDateFormat("yyyy-MM-dd")
            var format2 = SimpleDateFormat("dd-MM-yyyy")

            var date : Date = format1.parse(strDate)
            var formattedDate = format2.format(date)

            return formattedDate

        }
    }
}