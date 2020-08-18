package com.example.movienight.ui.uiModels

import kotlin.properties.Delegates

class PopularMovieUi() {
    lateinit var title:String
    lateinit var releaseDate:String
    lateinit var overview:String
    var voteAverage by Delegates.notNull<Double>()
    var adult by Delegates.notNull<Boolean>()
    lateinit var posterPath:String
}