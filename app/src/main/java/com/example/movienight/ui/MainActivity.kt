package com.example.movienight.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movienight.R
import com.example.movienight.ui.movieDetails.MovieDetailsFragment
import com.example.movienight.ui.popularMovies.PopularMoviesFragment
import com.example.movienight.ui.base.BaseActivity
import com.example.movienight.ui.base.Constants

class MainActivity : BaseActivity<MainViewModel>() {

    override fun layoutID(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.isLoading.postValue(false)
    }

    override fun getViewModel(): MainViewModel {
        return MainViewModel()
    }
}