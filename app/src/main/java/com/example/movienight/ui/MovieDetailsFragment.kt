package com.example.movienight.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movienight.R
import com.example.movienight.models.movieDetails.MovieDetails
import kotlinx.android.synthetic.main.movie_details_fragment.*
import kotlin.properties.Delegates

class MovieDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var movieDetails:MovieDetails
    private var movieID by Delegates.notNull<Int>()

    fun updateUI(movieDetails: MovieDetails) {
        movie_title_middle.setText(movieDetails.title)
        var genresText=movieDetails.genres[0].name
        for(i in (1..movieDetails.genres.size-1)){
            genresText=genresText+", "
            genresText=genresText+movieDetails.genres[i].name
        }


        genres_text.setText(genresText)
        overview_text.setText(movieDetails.overview)
        date_text.setText(movieDetails.releaseDate)
        quote_text.setText(movieDetails.tagline)
        rating_text.setText(movieDetails.voteAverage.toString())
        language_text.setText(movieDetails.spokenLanguages[0].name)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original" + movieDetails.backdropPath)
            .into(movie_image)
    }

    override fun layoutID(): Int {
        return R.layout.movie_details_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieID= arguments?.getInt("movie_id")!!
        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.setID(movieID)
        movieDetailsViewModel.requsetMovieDetails()
        movieDetailsViewModel.getMovieDetails().observe(viewLifecycleOwner,object:Observer<MovieDetails>{
            override fun onChanged(t: MovieDetails?) {
                if(movieDetailsViewModel.getIsLoading().value==false){
                    movieDetails= movieDetailsViewModel.getMovieDetails().value!!
                    progress_bar_movie_details.visibility=View.GONE
                    nested_scroll_movie_details.visibility=View.VISIBLE
                    app_bar.visibility=View.VISIBLE
                    updateUI(movieDetails)
                }
            }
        })


    }



}