package com.example.movienight.ui.popularMovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movienight.models.movies.Movies
import com.example.movienight.models.movies.Result
import com.example.movienight.repository.ApiEndPoints
import com.example.movienight.repository.RetrofitClient
import com.example.movienight.ui.utilities.BaseViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesViewModel : BaseViewModel() {
    val movieListLive=MutableLiveData<List<Result>>()
    private val failed=MutableLiveData<Boolean>(false)
    fun requestMovies(){

        viewModelScope.launch(IO) {
            try {
                val response = RetrofitClient.getAPI(ApiEndPoints::class.java).getPopularMovies()
                if (response.isSuccessful) {
                    movieListLive.postValue(response.body()?.results)
                    isLoading.postValue(false)
                } else {
                    isLoading.postValue(true)
                }
            }
            catch (err:Throwable){
                failed.postValue(true)
            }
        }

    }

    fun getMovies():LiveData<List<Result>>{
        return movieListLive
    }


}