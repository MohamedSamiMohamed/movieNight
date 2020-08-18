package com.example.movienight.ui.popularMovies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.R
import com.example.movienight.models.movies.Result
import com.example.movienight.ui.utilities.BaseFragment
import com.example.movienight.ui.MainActivity
import com.example.movienight.ui.movieDetails.MovieDetailsFragment
import com.example.movienight.ui.uiModels.PopularMovieUi
import com.example.movienight.ui.utilities.Constants
import kotlinx.android.synthetic.main.popular_movies_fragment.*

class PopularMoviesFragment : BaseFragment<PopularMoviesViewModel>(),
    MoviesAdapter.OnItemClickListener {

    companion object {
        fun newInstance() =
            PopularMoviesFragment()
    }

    private lateinit var moviesAdapter: MoviesAdapter

    override fun layoutID(): Int {
        return R.layout.popular_movies_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie_list_rv.layoutManager = LinearLayoutManager(this.activity)
        moviesAdapter =
            MoviesAdapter(this)
        movie_list_rv.adapter = moviesAdapter

        mViewModel.requestMovies()
        mViewModel.movieListUI.observe(viewLifecycleOwner,
            Observer<List<PopularMovieUi>> {
                moviesAdapter.setMoviesList(it)
                moviesAdapter.notifyDataSetChanged()
            })
    }

    override fun onItemClick(position: Int) {
        val args = Bundle()
        mViewModel.movieListLive.value?.get(position)?.id?.let { args.putInt(Constants.MOVIE_ID, it) }
        val movieDetails =
            MovieDetailsFragment.newInstance()
        movieDetails.arguments = args
        (activity as MainActivity).replaceFragment(movieDetails, Constants.MOVIE_DETAILS_TAG)
    }

    override fun getViewModel(): PopularMoviesViewModel = PopularMoviesViewModel()

}