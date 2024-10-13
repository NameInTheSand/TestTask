package com.ukrdroiddev.testtask.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukrdroiddev.testtask.TestApp
import com.ukrdroiddev.testtask.domain.useCases.BootTimeUseCase
import com.ukrdroiddev.testtask.presentation.workers.WorkersManager
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val workersManager: WorkersManager,
    private val bootUseCase: BootTimeUseCase
) : ViewModel() {

    private val _lastBootDetectionTime = MutableLiveData<String>()
    val lastBootDetectionTime: LiveData<String> = _lastBootDetectionTime


    init {
        updateLastBootDetectionTime()
    }

    fun scheduleRepeatingNotification() {
        workersManager.scheduleRepeatingNotification(TestApp.appContext!!)
    }

    fun updateLastBootDetectionTime() {
        viewModelScope.launch {
            _lastBootDetectionTime.value = bootUseCase.invoke(TestApp.appContext!!).getOrNull()!!
        }
    }

}