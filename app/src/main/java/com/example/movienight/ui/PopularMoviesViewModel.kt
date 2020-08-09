package com.example.movienight.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movienight.models.movies.Movies
import com.example.movienight.models.movies.Result
import com.example.movienight.repository.ApiEndPoints
import com.example.movienight.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesViewModel : ViewModel() {
    val movieListLive=MutableLiveData<List<Result>>()
    val isLoading=MutableLiveData<Boolean>()
    val failed=MutableLiveData<Boolean>(false)
    fun requestMovies(){
        val request= RetrofitClient.getAPI(ApiEndPoints::class.java).getPopularMovies()
        request.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                failed.value=true
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful){
                    movieListLive.value=response.body()?.results
                    Log.d("RESPONSE",movieListLive.value.toString())
                    isLoading.value=false
                }
                else{
                    isLoading.value=true
                }
            }
        })
    }
    fun getMovies():LiveData<List<Result>>{
        return movieListLive
    }
    fun getIsLoading():LiveData<Boolean>{
        return  isLoading
    }
    fun isFailed():LiveData<Boolean>{
        return  failed
    }

}