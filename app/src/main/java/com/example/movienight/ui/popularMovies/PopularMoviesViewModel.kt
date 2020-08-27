package com.example.movienight.ui.popularMovies

import androidx.lifecycle.*
import com.example.movienight.data.repository.PopularMoviesRepo
import com.example.movienight.ui.popularMovies.model.PopularMovieUi
import com.example.movienight.ui.base.BaseViewModel


class PopularMoviesViewModel : BaseViewModel<PopularMoviesRepo>() {
    var movieListUI: LiveData<List<PopularMovieUi>> = MutableLiveData()

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