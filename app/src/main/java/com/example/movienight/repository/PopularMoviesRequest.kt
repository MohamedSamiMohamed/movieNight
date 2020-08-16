package com.example.movienight.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movienight.models.movies.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PopularMoviesRequest {
    private val _popularMoviesResponse = MutableLiveData<List<Result>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _failed = MutableLiveData<Boolean>(false)
    val popularMoviesResponse: LiveData<List<Result>>
        get() = _popularMoviesResponse
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val failed: LiveData<Boolean>
        get() = _failed

    suspend fun requestMovies() {
        try {
            val response = RetrofitClient.getAPI(ApiEndPoints::class.java).getPopularMovies()
            if (response.isSuccessful) {
                _popularMoviesResponse.value=response.body()?.results
                _isLoading.value=false
            } else {
                _isLoading.value=true
            }
        } catch (err: Throwable) {
                _isLoading.value=true
                _failed.value=true
        }
    }

}
