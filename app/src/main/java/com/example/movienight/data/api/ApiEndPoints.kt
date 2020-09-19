package com.example.movienight.data.api

import com.example.movienight.constants.ApiConstants
import com.example.movienight.models.requestModels.MovieRatingData
import com.example.movienight.models.respnseModels.movieDetails.MovieDetails
import com.example.movienight.models.popularMovies.Movies
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
const val API_KEY= ApiConstants.API_KEY
const val SESSION_ID= ApiConstants.SESSION_ID

interface ApiEndPoints {
    /**
     *this method to get popular movies info
     */
    @GET("3/movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies() : Response<Movies>

    /**
     *this method is to get specific movie details
     */
    @GET("3/movie/{movie_id}?api_key=$API_KEY")
    suspend fun getMovieDetails(@Path("movie_id") movieID:Int) : Response<MovieDetails>

    @POST("3/movie/{movie_id}/rating?api_key=$API_KEY&session_id=$SESSION_ID")
    suspend fun rateMovie(@Path("movie_id") movieID: Int,@Body movieRatingData: MovieRatingData):Response<ResponseBody>
}