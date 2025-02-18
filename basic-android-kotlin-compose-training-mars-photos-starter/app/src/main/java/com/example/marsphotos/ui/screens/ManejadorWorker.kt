package com.example.marsphotos.ui.screens


import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object ManejadorWorker {
    fun scheduleHourlyTask(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(
            1, TimeUnit.HOURS // Intervalo de 1 hora
        ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "HourlyTask",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }
}