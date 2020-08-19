package com.example.movienight.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.repository.MovieDetailsRepo
import com.example.movienight.ui.movieDetails.models.MovieDetailsUi
import com.example.movienight.ui.base.BaseViewModel


class MovieDetailsViewModel() : BaseViewModel<MovieDetailsRepo>() {

    var movieID: Int? = null
    lateinit var movieDetailsUI: LiveData<MovieDetailsUi>

    fun requestMovieDetails() {
        isLoading.value = true
        movieDetailsUI = Transformations.switchMap(mRepo.requestMovieDetails(movieID!!)) {
            isLoading.value = false
            MutableLiveData(MovieDetailsUi.convertToUiModel(it))
        }

    }


    override fun getRepo(): MovieDetailsRepo {
        return MovieDetailsRepo()
    }
}




