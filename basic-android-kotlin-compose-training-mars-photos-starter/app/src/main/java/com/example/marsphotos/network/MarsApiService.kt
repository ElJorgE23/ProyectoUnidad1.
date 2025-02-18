package com.example.exchangerates.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://v6.exchangerate-api.com/v6/afadd315975914474e7f2abe/latest/USD/"

private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface ExchangeRateApiService {
    @GET(".")
    suspend fun getExchangeRates(): ExchangeRateResponse
}

object ExchangeRateApi {
    val retrofitService: ExchangeRateApiService by lazy {
        retrofit.create(ExchangeRateApiService::class.java)
    }
}
