package com.ukrdroiddev.testtask.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateFormatUtil {

    private val fullDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())


    fun formatDate(date: Date): String {
        return fullDateFormat.format(date)
    }

}