package com.ukrdroiddev.testtask.koin

import com.ukrdroiddev.testtask.data.database.AppDatabase
import com.ukrdroiddev.testtask.domain.useCases.BootTimeUseCase
import com.ukrdroiddev.testtask.domain.utils.DateFormatUtil
import com.ukrdroiddev.testtask.presentation.notifications.NotificationsManager
import com.ukrdroiddev.testtask.presentation.viewModels.MainActivityViewModel
import com.ukrdroiddev.testtask.presentation.workers.WorkersManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// To save time creating 1 module for 3 layers

val appModule = module {
    //Presentation
    single { NotificationsManager(get()) }
    single { WorkersManager() }
    viewModel { MainActivityViewModel(get(), get()) }

    //Domain
    single { DateFormatUtil() }
    single { BootTimeUseCase(get(), get()) }

    //Data
    single { AppDatabase.getDatabase(get()) }
    single { get<AppDatabase>().bootEventDao() }
}