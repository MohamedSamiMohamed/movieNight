package com.example.movienight

import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("app:setAdapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("app:setImage")
fun setImage(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view.context)
            .load("https://image.tmdb.org/t/p/original$url")
            .into(view)
    }
}

@BindingAdapter("app:setGenres")
fun setGenres(view: TextView, genres: List<String>?) {
    if (genres != null) {
        var genresText = ""
        for (genre in genres - 1) {
            genresText += genre
            genresText = "$genresText, "
        }
        genresText = genresText.dropLast(2)
        view.text = genresText
    }
}

@BindingAdapter("app:showInputTextError")
fun showError(view: EditText, errMessage: String?) {
    if (errMessage != null) {
        view.error = errMessage
        view.requestFocus()
    }
}
