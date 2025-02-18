package com.example.marsphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider

import com.example.exchangerates.ui.screens.MainViewModel
import com.example.marsphotos.ui.theme.ExchangeRatesTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()  // No se necesita factory aquí

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Establecer la tarea de WorkManager
        viewModel.scheduleWork()

        // Establecer el contenido de la actividad
        setContent {
            ExchangeRatesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("WorkManager App")
                }
            }
        }
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hola, $name")
}