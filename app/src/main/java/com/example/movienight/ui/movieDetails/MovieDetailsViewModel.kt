package com.example.movienight.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.repository.ApiEndPoints
import com.example.movienight.repository.RetrofitClient
import com.example.movienight.ui.utilities.BaseViewModel
import kotlinx.coroutines.launch


class MovieDetailsViewModel() : BaseViewModel() {
    var movieID=MutableLiveData<Int>()

    private val failed= MutableLiveData<Boolean>(false)
    val movieDetailsLive=MutableLiveData<MovieDetails>()

    //method to get movieDetails
    fun requsetMovieDetails(){
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
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


    fun setID(movieId: Int){
        movieID.value=movieId
    }
}




