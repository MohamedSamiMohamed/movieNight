package com.example.movienight

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("app:setAdapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("app:setImage")
fun setImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load("https://image.tmdb.org/t/p/original$url")
        .into(view)
}

@BindingAdapter("app:setGenres")
fun setGenres(view: TextView, genres: List<String>) {
    var genresText = genres[0]
    for (i in (1..genres.size - 1)) {
        genresText = genresText + ", "
        genresText = genresText + genres[i]
    }
    view.text = genresText
}

fun defaultGenres(): List<String>{
    return listOf("comedy","action")
}