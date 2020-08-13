package com.example.movienight.repository

import com.example.movienight.models.movieDetails.MovieDetails
import com.example.movienight.models.movies.Movies
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
const val API_KEY="11fab629496b64e02580a61604b7a093"
interface ApiEndPoints {
    /**
     *this method to get popular movies info
     */
    @GET("3/movie/popular?api_key=${API_KEY}")
    suspend fun getPopularMovies() : Response<Movies>

    /**
     *this method is to get specific movie details
     */
    @GET("3/movie/{movie_id}?api_key=${API_KEY}")
    suspend fun getMovieDetails(@Path("movie_id") movieID:Int) : Response<MovieDetails>
}