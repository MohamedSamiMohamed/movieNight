package com.example.movienight.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.example.movienight.R
import com.example.movienight.models.movieDetails.MovieDetails
import kotlinx.android.synthetic.main.movie_details_fragment.*
import org.w3c.dom.Text
import kotlin.properties.Delegates

class MovieDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()

    }
    private var movieID by Delegates.notNull<Int>()
    private lateinit var nestedScroll:NestedScrollView
    private lateinit var progressBar: ProgressBar
    private lateinit var movieImage:ImageView
    private lateinit var  appBar:View
    private lateinit var movieTitle:TextView
    private lateinit var movieGenres:TextView
    private lateinit var movieRating:TextView
    private lateinit var movieLanguage:TextView
    private lateinit var movieQuote:TextView
    private lateinit var movieDate:TextView
    private lateinit var movieOverView:TextView
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var movieDetails: MovieDetails
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.movie_details_fragment, container, false)
         movieID= arguments?.getInt("movie_id")!!
         appBar=view.findViewById(R.id.app_bar)
         movieTitle=view.findViewById(R.id.movie_title_middle)
         movieGenres=view.findViewById(R.id.genres_text)
         movieRating=view.findViewById(R.id.rating_text)
         movieLanguage=view.findViewById(R.id.language_text)
         movieQuote=view.findViewById(R.id.quote_text)
         movieDate=view.findViewById(R.id.date_text)
         movieOverView=view.findViewById(R.id.overview_text)
         movieImage=view.findViewById(R.id.movie_image)
         nestedScroll=view.findViewById(R.id.nested_scroll_movie_details)
        progressBar=view.findViewById(R.id.progress_bar_movie_details)
        return view
    }
    fun updateUI(movieDetails: MovieDetails) {

        movieTitle.setText(movieDetails.title)
        var genresText=movieDetails.genres[0].name
        for(i in (1..movieDetails.genres.size-1)){
            genresText=genresText+", "
            genresText=genresText+movieDetails.genres[i].name
        }
        movieGenres.setText(genresText)
        movieOverView.setText(movieDetails.overview)
        movieDate.setText(movieDetails.releaseDate)
        movieQuote.setText(movieDetails.tagline)
        movieRating.setText(movieDetails.voteAverage.toString())
        movieLanguage.setText(movieDetails.spokenLanguages[0].name)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original" + movieDetails.backdropPath)
            .into(movieImage)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.setID(movieID)
        movieDetailsViewModel.requsetMovieDetails()
        movieDetailsViewModel.getMovieDetails().observe(viewLifecycleOwner,object:Observer<MovieDetails>{
            override fun onChanged(t: MovieDetails?) {
                if(movieDetailsViewModel.getIsLoading().value==false){
                    movieDetails= movieDetailsViewModel.getMovieDetails().value!!
                    progressBar.visibility=View.GONE
                    nestedScroll.visibility=View.VISIBLE
                    appBar.visibility=View.VISIBLE
                    updateUI(movieDetails)
                }
            }
        })


    }



}