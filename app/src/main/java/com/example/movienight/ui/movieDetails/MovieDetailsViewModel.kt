package com.example.movienight.ui.movieDetails

import androidx.lifecycle.*
import com.example.movienight.models.requestModels.MovieRatingData
import com.example.movienight.data.repository.MovieDetailsRepo
import com.example.movienight.ui.movieDetails.models.MovieDetailsUi
import com.example.movienight.ui.base.BaseViewModel
import com.example.movienight.utilities.Constants


class MovieDetailsViewModel(movieDetailsRepo: MovieDetailsRepo) : BaseViewModel<MovieDetailsRepo>(movieDetailsRepo) {

    var movieID: Int? = null
    lateinit var movieDetailsUI: LiveData<MovieDetailsUi>
    lateinit var ratingBody: MovieRatingData
    var rateText = MutableLiveData<String>()
    var errMessage = MutableLiveData<String>()
    var successRating: LiveData<Boolean> = MutableLiveData<Boolean>()

    fun requestMovieDetails() {
        isLoading.value = true
        movieDetailsUI = Transformations.switchMap(repo.requestMovieDetails(movieID!!)) {
            isLoading.value = false
            MutableLiveData(MovieDetailsUi.convertToUiModel(it))
        }

    }

    fun onRatingClick() {
        val rate: Float
        if (!rateText.value.isNullOrEmpty()) {
            rate = rateText.value!!.toFloat()
            if (validateRating(rate)) {
                isLoading.value = true
                ratingBody =
                    MovieRatingData(
                        rate
                    )
                successRating = Transformations.map(repo.rateMovie(movieID!!, ratingBody)) {
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

    private fun validateRating(rate: Float): Boolean {
        if (rate in 0.5..10.0 && (rate.rem(0.5)).compareTo(0.0) == 0) {
            return true
        }
        return false
    }


}




