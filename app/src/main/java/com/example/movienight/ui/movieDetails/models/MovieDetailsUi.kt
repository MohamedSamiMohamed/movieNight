package com.example.movienight.ui.movieDetails.models

import com.example.movienight.models.movieDetails.Genre
import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.models.movieDetails.SpokenLanguage

class MovieDetailsUi {
    var dataVisibility:Boolean=false
    lateinit var genres: List<String>
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
            movieDetailsUiObject.dataVisibility=true
            movieDetailsUiObject.spokenLanguages = it.spokenLanguages
            movieDetailsUiObject.backdropPath = it.backdropPath
            movieDetailsUiObject.genres = it.genres.map {
                it.name
            }
            movieDetailsUiObject.overview = it.overview
            movieDetailsUiObject.title = it.title
            movieDetailsUiObject.releaseDate = it.releaseDate
            movieDetailsUiObject.voteAverage = it.voteAverage
            movieDetailsUiObject.tagline = it.tagline
            return movieDetailsUiObject
        }
    }

}
