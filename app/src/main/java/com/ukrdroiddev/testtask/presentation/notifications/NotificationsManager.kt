package com.ukrdroiddev.testtask.presentation.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ukrdroiddev.testtask.R

private const val BOOT_CHANNEL_ID = "boot_channel"
private const val BOOT_CHANNEL_NAME = "Boot Counter"
private const val BOOT_NOTIFICATION_ID = 1

class NotificationsManager(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(
        Context.NOTIFICATION_SERVICE
    ) as NotificationManager

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                BOOT_CHANNEL_ID,
                BOOT_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for Boot Counter app"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showBootNotification(content: String) {
        val notification = NotificationCompat.Builder(context, "boot_channel")
            .setContentTitle("Boot Event Detected")
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()


        notificationManager.notify(BOOT_NOTIFICATION_ID, notification)
    }
}