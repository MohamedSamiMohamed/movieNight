package com.example.movienight.ui.uiModels

import com.example.movienight.models.movieDetails.Genre
import com.example.movienight.models.movieDetails.SpokenLanguage
import kotlin.properties.Delegates

class MovieDetailsUi {

    lateinit var genres: List<Genre>
    lateinit var title:String
    lateinit var overview:String
    lateinit var releaseDate:String
    lateinit var tagline:String
    lateinit var spokenLanguages:List<SpokenLanguage>
    var voteAverage by Delegates.notNull<Double>()
    lateinit var backdropPath:String

}
