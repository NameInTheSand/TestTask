package com.ukrdroiddev.testtask

import android.app.Application
import android.content.Context
import com.ukrdroiddev.testtask.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(this@TestApp)
            modules(appModule)
        }
    }


    companion object {
        var appContext: Context? = null
            private set
    }
}