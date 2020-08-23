package com.example.movienight.ui.popularMovies

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.R
import com.example.movienight.ui.base.BaseFragment
import com.example.movienight.ui.MainActivity
import com.example.movienight.ui.movieDetails.MovieDetailsFragment
import com.example.movienight.ui.popularMovies.adapter.PopularMoviesAdapter
import com.example.movienight.ui.base.Constants
import com.example.movienight.ui.movieDetails.MovieDetailsFragmentArgs
import kotlinx.android.synthetic.main.popular_movies_fragment.*

class PopularMoviesFragment : BaseFragment<PopularMoviesViewModel>(),
    PopularMoviesAdapter.OnItemClickListener {

    companion object {
        fun newInstance() =
            PopularMoviesFragment()
    }

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var navController: NavController
    override fun layoutID(): Int {
        return R.layout.popular_movies_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        movie_list_rv.layoutManager = LinearLayoutManager(this.activity)
        popularMoviesAdapter =
            PopularMoviesAdapter(this)
        movie_list_rv.adapter = popularMoviesAdapter

        mViewModel.requestMovies()
        mViewModel.movieListUI.observe(viewLifecycleOwner,
            Observer {
                popularMoviesAdapter.setMoviesList(it)
                popularMoviesAdapter.notifyDataSetChanged()
            })
    }

    override fun onItemClick(position: Int) {
        val args = bundleOf(Constants.MOVIE_ID to mViewModel.movieListUI.value?.get(position)?.id)
        navController.navigate(R.id.action_popularMoviesFragment_to_movieDetailsFragment,args)
    }

    override fun getViewModel(): PopularMoviesViewModel = PopularMoviesViewModel()

}