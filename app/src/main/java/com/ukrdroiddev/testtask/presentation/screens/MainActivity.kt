package com.ukrdroiddev.testtask.presentation.screens

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ukrdroiddev.testtask.R
import com.ukrdroiddev.testtask.presentation.viewModels.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateLastBootDetectionTime()
//        viewModel.scheduleRepeatingNotification()
    }


    private fun initObservers() {
        viewModel.lastBootDetectionTime.observe(this) { time ->
            findViewById<TextView>(R.id.boot_time).text = time
        }
    }
}