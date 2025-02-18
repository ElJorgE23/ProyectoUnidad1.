package com.example.exchangerates.ui.screens

import BaseDeDatos.InicioBD
import android.app.Application
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerates.network.ExchangeRateApi
import com.example.exchangerates.network.ExchangeRateResponse
import com.example.marsphotos.ui.screens.ManejadorWorker
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ExchangeRatesUiState {
    data class Success(val rates: ExchangeRateResponse) : ExchangeRatesUiState
    object Error : ExchangeRatesUiState
    object Loading : ExchangeRatesUiState
}
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    fun scheduleWork() {
        ManejadorWorker.scheduleHourlyTask(context)
    }

    init {
        fetchExchangeRates()
    }

    private fun fetchExchangeRates() {
        viewModelScope.launch {
            // Ahora puedes usar el context aquí sin necesidad de pasarlo al constructor
            val database = InicioBD.getDatabase(context)
            val exchangeRates = database.exchangeRateDao().getExchangeRatesByDate(System.currentTimeMillis())
            exchangeRates.forEach {
                Log.d("Database", "${it.currency}: ${it.rate}")
            }
        }
    }
}