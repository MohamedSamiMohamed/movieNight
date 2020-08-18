package com.example.movienight.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.ui.utilities.BaseRepo
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieDetailsRepo : BaseRepo() {
    private val _movieDetailsLive = MutableLiveData<MovieDetails>()

    fun requestMovieDetails(movieID: Int): LiveData<MovieDetails> {
        GlobalScope.launch(Main) {
            try {
                val response =
                    RetrofitClient.getAPI(ApiEndPoints::class.java).getMovieDetails(movieID)
                if (response.isSuccessful) {
                    _movieDetailsLive.value = response.body()
                } else {
                    requestErrorMessage.value=response.errorBody().toString()
                }
            } catch (err: Throwable) {
                requestErrorMessage.value=err.message.toString()

            }
        }
        return _movieDetailsLive
    }
}