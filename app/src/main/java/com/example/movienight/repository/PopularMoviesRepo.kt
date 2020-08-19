package com.example.movienight.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movienight.models.movies.Result
import com.example.movienight.ui.base.BaseRepo
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PopularMoviesRepo:BaseRepo() {
    private val _popularMoviesResponse = MutableLiveData<List<Result>>()

    fun requestMovies() :LiveData<List<Result>> {
        GlobalScope.launch(Main) {
            try {
                val response = RetrofitClient.getAPI(ApiEndPoints::class.java).getPopularMovies()
                if (response.isSuccessful) {
                    _popularMoviesResponse.value = response.body()?.results
                } else {
                    requestErrorMessage.value = response.errorBody().toString()
                }

            } catch (err: Throwable) {
                requestErrorMessage.value = err.message.toString()
            }
        }
        return _popularMoviesResponse
    }
}
