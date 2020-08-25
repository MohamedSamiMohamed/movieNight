package com.example.movienight.ui.popularMovies.model

import com.example.movienight.models.movies.Result
import kotlin.properties.Delegates

class PopularMovieUi {
    var id: Int? = null
    lateinit var title: String
    lateinit var releaseDate: String
    lateinit var overview: String
    var voteAverage: Double? = null
    var adult: Boolean? = null
    lateinit var posterPath: String

    companion object {
        fun convertToUiModel(it: Result): PopularMovieUi {
            val popularMovieUiObject = PopularMovieUi()
            popularMovieUiObject.id = it.id
            popularMovieUiObject.releaseDate = it.releaseDate
            popularMovieUiObject.title = it.title
            popularMovieUiObject.adult = it.adult
            popularMovieUiObject.posterPath = it.posterPath
            popularMovieUiObject.voteAverage = it.voteAverage
            popularMovieUiObject.overview = it.overview
            return popularMovieUiObject
        }
    }
}