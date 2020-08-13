package com.example.movienight.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.repository.ApiEndPoints
import com.example.movienight.repository.RetrofitClient
import kotlinx.coroutines.launch


class MovieDetailsViewModel() : ViewModel() {
    var movieID=MutableLiveData<Int>()

    private val isLoading= MutableLiveData<Boolean>()
    private val failed= MutableLiveData<Boolean>(false)
    private val movieDetailsLive=MutableLiveData<MovieDetails>()

    //method to get movieDetails
    fun requsetMovieDetails(){
        viewModelScope.launch {
            try {
                val response = movieID.value?.let {
                    RetrofitClient.getAPI(ApiEndPoints::class.java).getMovieDetails(
                        it
                    )
                }
                if (response?.isSuccessful!!) {
                    isLoading.postValue(false)
                    movieDetailsLive.postValue(response.body())
                } else {
                    isLoading.postValue(true)
                }

            }
            catch (err:Throwable){
                failed.postValue(true)
            }
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




