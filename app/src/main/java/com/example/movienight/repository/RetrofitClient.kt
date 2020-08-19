package com.example.movienight.repository

import com.example.movienight.ui.base.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = Constants.BASE_URL
    private val httpClientBuilder= OkHttpClient.Builder().build()
    private val retrofitClient=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClientBuilder)
        .build()
    fun <T> getAPI(API :Class<T>): T{
        return retrofitClient.create(API)
    }

}