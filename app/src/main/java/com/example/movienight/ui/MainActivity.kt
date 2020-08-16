package com.example.movienight.ui

import android.os.Bundle
import com.example.movienight.R
import com.example.movienight.ui.movieDetails.MovieDetailsFragment
import com.example.movienight.ui.popularMovies.PopularMoviesFragment
import com.example.movienight.ui.utilities.BaseActivity
import com.example.movienight.ui.utilities.Constants

class MainActivity : BaseActivity<MainViewModel>() {

    override fun layoutID(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.isLoading.postValue(false)
        supportFragmentManager.beginTransaction().add(R.id.root_layout,
            PopularMoviesFragment.newInstance(),Constants.POPULAR_MOVIES_TAG)
            .commit()
    }

    fun replaceFragment(fragment: MovieDetailsFragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.root_layout, fragment, tag).addToBackStack("").commit()
    }

    override fun getViewModel(): MainViewModel {
        return MainViewModel()
    }
}