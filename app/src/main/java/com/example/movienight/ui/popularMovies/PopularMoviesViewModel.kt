package com.example.movienight.ui.popularMovies

import androidx.lifecycle.*
import com.example.movienight.repository.PopularMoviesRepo
import com.example.movienight.ui.popularMovies.model.PopularMovieUi
import com.example.movienight.ui.base.BaseViewModel


class PopularMoviesViewModel : BaseViewModel<PopularMoviesRepo>() {
    lateinit var movieListUI: LiveData<List<PopularMovieUi>>

    fun requestMovies() {
        isLoading.value = true
        movieListUI = Transformations.switchMap(mRepo.requestMovies()) {
            isLoading.value = false
            MutableLiveData(it.map {
                PopularMovieUi.convertToUiModel(it)
            })
        }

    }


    override fun getRepo(): PopularMoviesRepo {
        return PopularMoviesRepo()
    }
}