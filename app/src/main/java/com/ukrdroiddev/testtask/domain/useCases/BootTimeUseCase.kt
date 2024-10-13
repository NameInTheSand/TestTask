package com.ukrdroiddev.testtask.domain.useCases

import android.content.Context
import com.ukrdroiddev.testtask.R
import com.ukrdroiddev.testtask.data.database.AppDatabase
import com.ukrdroiddev.testtask.domain.utils.DateFormatUtil
import java.util.concurrent.TimeUnit

class BootTimeUseCase(
    private val appDatabase: AppDatabase,
    private val dateFormatUtil: DateFormatUtil
) : BaseUseCase<Context,String>() {

    override suspend fun execute(params: Context): String {
        return getNotificationContent(params)
    }

    private suspend fun getNotificationContent(context: Context): String {
        val bootEventsCount = getBootEventCount()
        return when (bootEventsCount) {
            0 -> context.getString(R.string.lbl_no_boots_detected)
            1 -> context.getString(R.string.fmt_boot_detected, getLastBootTime(context))
            else -> context.getString(R.string.fmt_boot_delta, getTimeBetweenLastBoots(context))
        }
    }


    private suspend fun getBootEventCount(): Int {
        return appDatabase.bootEventDao().getBootEventCount()
    }

    private suspend fun getLastBootTime(context: Context): String {
        val lastBootEvents = appDatabase.bootEventDao().getLastBootEvents()
        return dateFormatUtil.formatDate(lastBootEvents[0].bootTime)
    }

    private suspend fun getTimeBetweenLastBoots(context: Context): String {
        val lastBootEvents = appDatabase.bootEventDao().getLastBootEvents()
        val lastTime = lastBootEvents[0].bootTime.time
        val secondLastTime = lastBootEvents[1].bootTime.time
        val delta = lastTime - secondLastTime

        val hours = TimeUnit.MILLISECONDS.toHours(delta)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(delta) % 60

        return context.getString(R.string.fmt_hours_minutes, hours, minutes)
    }
}