package com.example.movienight.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movienight.R
import com.example.movienight.models.movies.Result
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class MoviesViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.movie_image
        val movieTitle: TextView = itemView.movie_title
        val movieRating: TextView = itemView.rating_text
        val movieAdult: TextView = itemView.category_text
        val movieDate: TextView = itemView.date_text
        val movieOverView:TextView=itemView.overview_text

        fun bindData(movieData: Result) {
            movieTitle.setText(movieData.title)
            movieDate.setText(movieData.releaseDate)
            movieOverView.setText(movieData.overview)
            movieRating.setText(movieData.voteAverage.toString())
            if(movieData.adult==true){
                movieAdult.setText("watching under parents supervision!")
            }
            else{
                movieAdult.setText("family movie")
            }
            val requestOptions=RequestOptions().placeholder(R.mipmap.splash_icon).error(R.mipmap.splash_icon)
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/original"+movieData.posterPath)
                .into(movieImage)
        }
    }

    var popularMovies: List<Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        )
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MoviesViewHolder -> {
                holder.bindData(popularMovies.get(position))
            }
        }
    }

    fun setMoviesList(popularMoviesList: List<Result>) {
        popularMovies = popularMoviesList

    }

}