package com.example.movienight.ui.popularMovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movienight.models.movies.Result
import com.example.movienight.repository.PopularMoviesRequest
import com.example.movienight.ui.utilities.BaseViewModel
import kotlinx.coroutines.launch


class PopularMoviesViewModel : BaseViewModel() {
    val movieListLive=MutableLiveData<List<Result>>()
    private val failed=MutableLiveData<Boolean>()
    private val popularMoviesRequest =PopularMoviesRequest()


    fun requestMovies() {
        viewModelScope.launch() {
            popularMoviesRequest.requestMovies()
            failed.value=popularMoviesRequest.failed.value
            isLoading.value=popularMoviesRequest.isLoading.value
            if(!failed.value!!) {
                movieListLive.value = popularMoviesRequest.popularMoviesResponse.value
            }
        }
    }


    fun getMovies():LiveData<List<Result>>{
        return movieListLive
    }


}