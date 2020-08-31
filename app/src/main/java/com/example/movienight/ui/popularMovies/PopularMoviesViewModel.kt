package com.example.movienight.ui.popularMovies

import androidx.lifecycle.*
import com.example.movienight.data.repository.PopularMoviesRepo
import com.example.movienight.ui.popularMovies.model.PopularMovieUi
import com.example.movienight.ui.base.BaseViewModel


class PopularMoviesViewModel(private val popularMoviesRepo: PopularMoviesRepo) : BaseViewModel<PopularMoviesRepo>(popularMoviesRepo) {
    var movieListUI: LiveData<List<PopularMovieUi>> = MutableLiveData()

    fun requestMovies() {
        isLoading.value = true
        movieListUI = Transformations.switchMap(repo.requestMovies()) {
            isLoading.value = false
            MutableLiveData(it.map {
                PopularMovieUi.convertToUiModel(it)
            })
        }

    }


}