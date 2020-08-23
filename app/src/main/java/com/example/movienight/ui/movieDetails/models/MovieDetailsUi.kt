package com.example.movienight.ui.movieDetails.models

import com.example.movienight.models.movieDetails.Genre
import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.models.movieDetails.SpokenLanguage

class MovieDetailsUi {

    lateinit var genres: List<Genre>
    lateinit var title: String
    lateinit var overview: String
    lateinit var releaseDate: String
    lateinit var tagline: String
    lateinit var spokenLanguages: List<SpokenLanguage>
    var voteAverage: Double? = null
    lateinit var backdropPath: String

    companion object{
        fun convertToUiModel(it: MovieDetails):MovieDetailsUi {
            val movieDetailsUiObject =
                MovieDetailsUi()
            movieDetailsUiObject.spokenLanguages = it.spokenLanguages
            movieDetailsUiObject.backdropPath = it.backdropPath
            movieDetailsUiObject.genres = it.genres
            movieDetailsUiObject.overview = it.overview
            movieDetailsUiObject.title = it.title
            movieDetailsUiObject.releaseDate = it.releaseDate
            movieDetailsUiObject.voteAverage = it.voteAverage
            movieDetailsUiObject.tagline = it.tagline
            return movieDetailsUiObject
        }
    }

}