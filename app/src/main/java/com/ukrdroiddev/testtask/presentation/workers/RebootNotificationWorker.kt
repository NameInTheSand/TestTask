package com.ukrdroiddev.testtask.presentation.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ukrdroiddev.testtask.domain.useCases.BootTimeUseCase
import com.ukrdroiddev.testtask.presentation.notifications.NotificationsManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RebootNotificationWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams), KoinComponent {

    private val useCase: BootTimeUseCase by inject()
    private val notificationManager: NotificationsManager by inject()

    override suspend fun doWork(): Result {
        return showNotification()
    }

    private suspend fun showNotification(): Result {
        val content = useCase.invoke(context)
        if (content.isSuccess) {
            content.getOrNull()?.let { notificationManager.showBootNotification(it) }
            return Result.success()
        } else {
            return Result.failure()
        }
    }


}