package com.example.movienight.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movienight.models.MovieRatingData
import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.ui.base.BaseRepo
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieDetailsRepo : BaseRepo() {

    fun rateMovie(movieID: Int, movieRatingData: MovieRatingData): LiveData<Boolean> {
        val ratingSent = MutableLiveData<Boolean>()
        GlobalScope.launch(Main) {
            try {
                val response = RetrofitClient.getAPI(ApiEndPoints::class.java)
                    .rateMovie(movieID, movieRatingData)
                if (response.isSuccessful) {
                    ratingSent.value = true
                } else {
                    requestErrorMessage.value = response.errorBody().toString()
                }
            } catch (err: Throwable) {
                requestErrorMessage.value = err.message.toString()
            }
        }
        return ratingSent
    }

    fun requestMovieDetails(movieID: Int): LiveData<MovieDetails> {
        val movieDetailsLive = MutableLiveData<MovieDetails>()
        GlobalScope.launch(Main) {
            try {
                val response =
                    RetrofitClient.getAPI(ApiEndPoints::class.java).getMovieDetails(movieID)
                if (response.isSuccessful) {
                    movieDetailsLive.value = response.body()
                } else {
                    requestErrorMessage.value = response.errorBody().toString()
                }
            } catch (err: Throwable) {
                requestErrorMessage.value = err.message.toString()

            }
        }
        return movieDetailsLive
    }
}