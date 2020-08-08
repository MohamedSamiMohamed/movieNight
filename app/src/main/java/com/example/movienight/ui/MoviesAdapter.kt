package com.example.movienight.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.R
import com.example.movienight.models.movies.Result
import kotlinx.android.synthetic.main.movie_item.view.*

 class MoviesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     class MoviesViewHolder constructor(
        itemView:View
    ):RecyclerView.ViewHolder(itemView) {
        val movieImage:ImageView=itemView.movie_image
        val movieTitle:TextView=itemView.movie_title

        fun bindData(movieData:Result){
            movieTitle.setText(movieData.title)

        }
    }

    var popularMovies :List<Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
    return MoviesViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)

    )
    }

    override fun getItemCount(): Int {
    return popularMovies.size
    }

     override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MoviesViewHolder ->{
                holder.bindData(popularMovies.get(position))
            }
        }
     }
     fun setMoviesList(popularMoviesList: List<Result>){
             popularMovies=popularMoviesList

     }

 }