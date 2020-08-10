package com.example.movienight.ui.movieDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.repository.ApiEndPoints
import com.example.movienight.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel() : ViewModel() {
    var movieID=MutableLiveData<Int>()

    val isLoading= MutableLiveData<Boolean>()
    val failed= MutableLiveData<Boolean>(false)
    val movieDetailsLive=MutableLiveData<com.example.movienight.models.movieDetails.MovieDetails>()


    //method to get movieDetails
    fun requsetMovieDetails(){
        val request= movieID.value?.let {
            RetrofitClient.getAPI(ApiEndPoints::class.java).getMovieDetails(
                it
            )
        }

        if (request != null) {
            request.enqueue(object:Callback<MovieDetails>{
                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    failed.value=true
                    Log.d("RESPONSE DETAILS","Failed")

                }

                override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                    Log.d("RESPONSE DETAILS",response.body().toString())

                    if(response.isSuccessful){
                        isLoading.value=false
                        movieDetailsLive.value=response.body()
                        Log.d("RESPONSE DETAILS",response.body().toString())
                    }
                    else{
                        Log.d("RESPONSE DETAILS",response.body().toString())
                        isLoading.value=true
                    }
                }

            })
        }
        }


    fun getMovieDetails():LiveData<MovieDetails>{
        return movieDetailsLive
    }
    fun getIsLoading():LiveData<Boolean>{
        return  isLoading
    }
    fun isFailed():LiveData<Boolean>{
        return  failed
    }
    fun setID(movieId: Int){
        movieID.value=movieId
    }
}




