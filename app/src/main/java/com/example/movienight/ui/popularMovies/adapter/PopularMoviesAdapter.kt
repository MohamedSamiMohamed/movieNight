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

        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            clickListener.onItemClick(position)
        }

        fun bindData(position: Int) {
            binding.popularMovie = popularMovies[position]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context)))
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
