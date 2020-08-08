package com.example.movienight.repository

import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.models.movies.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
const val API_KEY="11fab629496b64e02580a61604b7a093"
interface ApiEndPoints {
    /**
     *
     */
    @GET("3/movie/popular?api_key=${API_KEY}")
    fun getPopularMovies() : Call<Movies>

    /**
     *
     */
    @GET("3/movie/{movie_id}?api_key=${API_KEY}")
    fun getMovieDetails(@Path("movie_id") movieID:Int) : Call<MovieDetails>
}