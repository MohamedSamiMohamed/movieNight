package com.example.movienight.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movienight.data.api.ApiEndPoints
import com.example.movienight.models.popularMovies.Result
import com.example.movienight.network.RetrofitClient
import com.example.movienight.data.base.BaseRepo
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PopularMoviesRepo : BaseRepo() {

    fun requestMovies(): LiveData<List<Result>> {
        val popularMoviesResponse = MutableLiveData<List<Result>>()
        GlobalScope.launch(Main) {
            try {
                val response = RetrofitClient.getAPI(ApiEndPoints::class.java).getPopularMovies()
                if (response.isSuccessful) {
                    popularMoviesResponse.value = response.body()?.results
                } else {
                    requestErrorMessage.value = response.errorBody().toString()
                }

            } catch (err: Throwable) {
                requestErrorMessage.value = err.message.toString()
            }
        }
        return popularMoviesResponse
    }
}
