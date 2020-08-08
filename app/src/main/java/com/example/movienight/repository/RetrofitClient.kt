package com.example.movienight.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val baseUrl="https://api.themoviedb.org/"
    private val httpClientBuilder= OkHttpClient.Builder().build()
    private val retrofitClient=Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClientBuilder)
        .build()
    fun <T> getAPI(API :Class<T>): T{
        return retrofitClient.create(API)
    }

}