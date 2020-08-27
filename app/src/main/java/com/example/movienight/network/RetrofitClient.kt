package com.example.movienight.network

import com.example.movienight.utilities.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = Constants.BASE_URL
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val httpClientBuilder= OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private val retrofitClient=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClientBuilder)
        .build()
    fun <T> getAPI(API :Class<T>): T{
        return retrofitClient.create(API)
    }

}
