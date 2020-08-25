package com.example.movienight

import android.widget.ImageView
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