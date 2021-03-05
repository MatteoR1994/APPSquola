package com.example.appsquola

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    private const val URL = "http://192.168.1.131:8080/api/"

    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder()
        .callTimeout(5, TimeUnit.SECONDS)
        .build()

    // Create Retrofit Builder
    private val retrofit = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp)
        .build()

    // Create Retrofit Instance
    //private val retrofit = builder

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}