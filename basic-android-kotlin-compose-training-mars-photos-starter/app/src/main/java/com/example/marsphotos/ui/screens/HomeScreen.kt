package com.example.exchangerates.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marsphotos.R
import com.example.marsphotos.ui.theme.ExchangeRatesTheme

@Composable
fun HomeScreen(
    exchangeRatesUiState: ExchangeRatesUiState, modifier: Modifier = Modifier
) {
    when (exchangeRatesUiState) {
        is ExchangeRatesUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is ExchangeRatesUiState.Success -> ResultScreen(
            exchangeRatesUiState.rates.toString(), modifier = modifier.fillMaxWidth()
        )

        is ExchangeRatesUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

/**
 * ResultScreen displaying exchange rates retrieved.
 */
@Composable
fun ResultScreen(rates: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = rates)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ExchangeRatesTheme {
        ResultScreen("Exchange rates go here")
    }
}
