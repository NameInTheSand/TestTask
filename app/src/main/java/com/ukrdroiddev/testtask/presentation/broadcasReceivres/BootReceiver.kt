package com.ukrdroiddev.testtask.presentation.broadcasReceivres

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ukrdroiddev.testtask.data.database.AppDatabase
import com.ukrdroiddev.testtask.data.entities.BootEventEntity
import com.ukrdroiddev.testtask.koin.appModule
import com.ukrdroiddev.testtask.presentation.workers.WorkersManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import java.util.Date

class BootReceiver : BroadcastReceiver(), KoinComponent {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {

            if (GlobalContext.getOrNull() == null) {
                startKoin {
                    androidContext(context)
                    modules(appModule)
                }
            }

            val appDatabase: AppDatabase by inject()
            val scope = CoroutineScope(Dispatchers.IO)
            val workersManager: WorkersManager by inject()
            scope.launch {
                persistBootEvent(appDatabase)
                scheduleRepeatingNotification(context, workersManager)
            }
            scope.cancel()
        }
    }

    private suspend fun persistBootEvent(appDatabase: AppDatabase) {
        val bootEvent = BootEventEntity(bootTime = Date())
        appDatabase.bootEventDao().insertBootEvent(bootEvent)
    }

    private fun scheduleRepeatingNotification(context: Context, workersManager: WorkersManager) {
        workersManager.startRebootNotificationWorker(context)
    }

}