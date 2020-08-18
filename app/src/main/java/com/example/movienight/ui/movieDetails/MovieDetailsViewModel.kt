package com.example.movienight.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.repository.MovieDetailsRepo
import com.example.movienight.ui.uiModels.MovieDetailsUi
import com.example.movienight.ui.utilities.BaseViewModel
import kotlin.properties.Delegates


class MovieDetailsViewModel() : BaseViewModel<MovieDetailsRepo>() {

    var movieID by Delegates.notNull<Int>()
    lateinit var movieDetailsLive : LiveData<MovieDetails>
    lateinit var movieDetailsUI: LiveData<MovieDetailsUi>

    fun requestMovieDetails() {
        isLoading.value = true
        movieDetailsLive = mRepo.requestMovieDetails(movieID)
        movieDetailsUI=Transformations.switchMap(movieDetailsLive){
            val movieDetailsUiObject=MovieDetailsUi()
            movieDetailsUiObject.spokenLanguages=it.spokenLanguages
            movieDetailsUiObject.backdropPath=it.backdropPath
            movieDetailsUiObject.genres=it.genres
            movieDetailsUiObject.overview=it.overview
            movieDetailsUiObject.title=it.title
            movieDetailsUiObject.releaseDate=it.releaseDate
            movieDetailsUiObject.voteAverage=it.voteAverage
            movieDetailsUiObject.tagline=it.tagline
            isLoading.value=false
            MutableLiveData(movieDetailsUiObject)
        }

    }

    fun setID(movieId: Int) {
        movieID = movieId
    }

    override fun getRepo(): MovieDetailsRepo {
        return MovieDetailsRepo()
    }
}




