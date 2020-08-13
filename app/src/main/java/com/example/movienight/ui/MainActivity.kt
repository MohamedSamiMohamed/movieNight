package com.example.movienight.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movienight.R
import com.example.movienight.ui.popularMovies.PopularMoviesFragment
import com.example.movienight.ui.utilities.BaseActivity

class MainActivity : BaseActivity() {

    lateinit var  mainViewModel:MainViewModel
    override fun layoutID(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(R.id.root_layout,
            PopularMoviesFragment.newInstance(),"popular_movies")
            .commit()
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.root_layout, fragment, tag).addToBackStack("").commit()
    }
}