package com.example.movienight.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.movienight.R
import com.example.movienight.models.movies.Movies
import com.example.movienight.repository.ApiEndPoints
import com.example.movienight.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.root_layout,PopularMovies.newInstance(),"popular_movies")
            .commit()
    }
}