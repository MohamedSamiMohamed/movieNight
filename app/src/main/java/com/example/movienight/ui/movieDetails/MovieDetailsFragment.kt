package com.example.movienight.ui.movieDetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movienight.R
import com.example.movienight.ui.movieDetails.models.MovieDetailsUi
import com.example.movienight.ui.base.BaseFragment
import com.example.movienight.ui.base.Constants
import kotlinx.android.synthetic.main.movie_details_fragment.*
import kotlin.properties.Delegates

class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>() {

    companion object {
        fun newInstance() =
            MovieDetailsFragment()
    }

    private var movieID:Int?=null

    fun updateUI(movieDetails: MovieDetailsUi) {
        movie_title_middle.setText(movieDetails.title)
        var genresText = movieDetails.genres[0].name
        for (i in (1..movieDetails.genres.size - 1)) {
            genresText = genresText + ", "
            genresText = genresText + movieDetails.genres[i].name
        }
        genres_text.setText(genresText)
        overview_text.setText(movieDetails.overview)
        date_text.setText(movieDetails.releaseDate)
        quote_text.setText(movieDetails.tagline)
        rating_text.setText(movieDetails.voteAverage.toString())
        language_text.setText(movieDetails.spokenLanguages[0].name)
        Glide.with(this)
            .load(Constants.IMAGE_URL + movieDetails.backdropPath)
            .into(movie_image)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieID = arguments?.getInt(Constants.MOVIE_ID)!!
        mViewModel.movieID = movieID
        mViewModel.requestMovieDetails()
        mViewModel.movieDetailsUI.observe(viewLifecycleOwner, Observer<MovieDetailsUi> {
            nested_scroll_movie_details.visibility = View.VISIBLE
            app_bar.visibility = View.VISIBLE
            updateUI(it)
        })
    }

    override fun layoutID(): Int {
        return R.layout.movie_details_fragment
    }

    override fun getViewModel(): MovieDetailsViewModel {
        return MovieDetailsViewModel()
    }


}