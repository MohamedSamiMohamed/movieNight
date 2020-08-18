package com.example.movienight.ui.popularMovies

import android.util.Log
import androidx.lifecycle.*
import com.example.movienight.models.movies.Result
import com.example.movienight.repository.PopularMoviesRepo
import com.example.movienight.ui.uiModels.PopularMovieUi
import com.example.movienight.ui.utilities.BaseViewModel
import kotlinx.coroutines.launch


class PopularMoviesViewModel : BaseViewModel<PopularMoviesRepo>() {
    lateinit var movieListLive: LiveData<List<Result>>
    lateinit var movieListUI: LiveData<List<PopularMovieUi>>

    fun requestMovies() {
        isLoading.value = true
        movieListLive = mRepo.requestMovies()
        movieListUI = Transformations.switchMap(movieListLive) {
            lateinit var popularMovies: List<PopularMovieUi>
            popularMovies = it.map {
                val popularMovieUiObject = PopularMovieUi()
                popularMovieUiObject.releaseDate = it.releaseDate
                popularMovieUiObject.title = it.title
                popularMovieUiObject.adult = it.adult
                popularMovieUiObject.posterPath = it.posterPath
                popularMovieUiObject.voteAverage = it.voteAverage
                popularMovieUiObject.overview = it.overview
                popularMovieUiObject
            }
            isLoading.value = false
            MutableLiveData(popularMovies)
        }

    }


    override fun getRepo(): PopularMoviesRepo {
        return PopularMoviesRepo()
    }
}