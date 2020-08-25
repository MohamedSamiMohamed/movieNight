package com.example.movienight.ui.popularMovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movienight.R
import com.example.movienight.databinding.MovieItemBinding
import com.example.movienight.ui.popularMovies.model.PopularMovieUi
import kotlinx.android.synthetic.main.movie_item.view.*

class PopularMoviesAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var popularMovies: List<PopularMovieUi> = ArrayList()

    inner class MoviesViewHolder(var binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        /*private val movieImage: ImageView = itemView.movie_image
        private val movieTitle: TextView = itemView.movie_title
        private val movieRating: TextView = itemView.rating_text
        private val movieAdult: TextView = itemView.category_text
        private val movieDate: TextView = itemView.date_text
        private val movieOverView: TextView = itemView.overview_text*/
        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            clickListener.onItemClick(position)
        }

        fun bindData(position: Int) {
//            movieTitle.setText(movieData.title)
//            movieDate.setText(movieData.releaseDate)
//            movieOverView.setText(movieData.overview)
//            movieRating.setText(movieData.voteAverage.toString())
//            if (movieData.adult == true) {
//                movieAdult.setText("watching under parents supervision!")
//            } else {
//                movieAdult.setText("family movie")
//            }
//            val requestOptions =
//                RequestOptions().placeholder(R.mipmap.splash_icon).error(R.mipmap.splash_icon)
//            Glide.with(itemView.context)
//                .load("https://image.tmdb.org/t/p/original" + movieData.posterPath)
//                .into(movieImage)
            binding.popularMovie = popularMovies[position]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(layoutInflater)
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MoviesViewHolder -> {
                holder.bindData(position)
            }
        }
    }

    fun setMoviesList(popularMoviesList: List<PopularMovieUi>) {
        popularMovies = popularMoviesList

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
