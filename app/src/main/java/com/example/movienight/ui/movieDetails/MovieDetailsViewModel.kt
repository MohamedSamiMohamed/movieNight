package com.example.movienight.ui.movieDetails

import android.util.Log
import androidx.lifecycle.*
import com.example.movienight.R
import com.example.movienight.models.MovieRatingData
import com.example.movienight.repository.MovieDetailsRepo
import com.example.movienight.ui.movieDetails.models.MovieDetailsUi
import com.example.movienight.ui.base.BaseViewModel
import com.example.movienight.ui.base.Constants
import com.google.android.play.core.tasks.Tasks.await
import kotlinx.coroutines.launch


class MovieDetailsViewModel() : BaseViewModel<MovieDetailsRepo>() {

    var movieID: Int? = null
    lateinit var movieDetailsUI: LiveData<MovieDetailsUi>
    lateinit var ratingBody: MovieRatingData
    var rateText = MutableLiveData<String>()
    var errMessage = MutableLiveData<String>()
    private var _successRating = MutableLiveData<Boolean>(false)
    var successRating: LiveData<Boolean> = _successRating

    fun requestMovieDetails() {
        isLoading.value = true
        movieDetailsUI = Transformations.switchMap(mRepo.requestMovieDetails(movieID!!)) {
            isLoading.value = false
            MutableLiveData(MovieDetailsUi.convertToUiModel(it))
        }

    }

    fun onRatingClick() {
        val rate: Float
        if (rateText.value != null && rateText.value != "") {
            rate = rateText.value!!.toFloat()
            if (validateRating(rate)) {
                isLoading.value = true
                ratingBody = MovieRatingData(rate)
                successRating = Transformations.map(mRepo.rateMovie(movieID!!, ratingBody)) {
                    isLoading.value = false
                    (it)
                }
            } else {
                errMessage.value = Constants.ERROR_RATING
            }
        } else {
            errMessage.value = Constants.ERROR_RATING
        }
    }

    fun validateRating(rate: Float): Boolean {
        if (rate in 0.5..10.0 && (rate.rem(0.5)).compareTo(0.0) == 0) {
            return true
        }
        return false
    }


    override fun getRepo(): MovieDetailsRepo {
        return MovieDetailsRepo()
    }
}




