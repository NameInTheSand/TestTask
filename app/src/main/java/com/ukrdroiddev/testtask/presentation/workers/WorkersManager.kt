package com.ukrdroiddev.testtask.presentation.workers

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import org.koin.core.component.KoinComponent
import java.util.concurrent.TimeUnit

class WorkersManager : KoinComponent {

    fun startRebootNotificationWorker(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<RebootNotificationWorker>()
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }

    fun scheduleRepeatingNotification(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<RebootNotificationWorker>(
            15, TimeUnit.MINUTES
        ).build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

}