package com.example.eventssicredi.utils

import android.os.Build
import android.util.Patterns
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

open class Util {

    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        fun getDateFormat(time : Long?, pattern: String): String {
            val date = time?.let { Date(it) } ?: return ""
            val format = SimpleDateFormat(pattern)
            return format.format(date) ?: ""
        }

        @JvmStatic
        fun changeLinkToHttps(valor: String?): String {
            return valor?.replace("http:", "https:") ?: ""
        }

        @JvmStatic
        fun getTimeFormat(time : LocalTime?): String {
            return time?.toString() ?: ""
        }

        @JvmStatic
        fun replaceInitialZeros(s: String?): String{
            return s?.trimStart('0') ?: ""
        }

        @JvmStatic
        fun replaceWithoutCharacters(s: String?): String{
            return s?.replace("[,.]".toRegex(), "") ?: "0"
        }

        @JvmStatic
        fun getRealCurrency(valor: Double?): String {
            val nf: NumberFormat = NumberFormat.getCurrencyInstance()
            return nf.format(valor) ?: ""
        }

        @JvmStatic
        fun validateEmailFormat(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}