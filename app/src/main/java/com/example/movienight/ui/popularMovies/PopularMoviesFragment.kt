package com.example.movienight.ui.popularMovies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movienight.R
import com.example.movienight.models.movies.Result
import com.example.movienight.ui.utilities.BaseFragment
import com.example.movienight.ui.MainActivity
import com.example.movienight.ui.movieDetails.MovieDetailsFragment
import kotlinx.android.synthetic.main.popular_movies_fragment.*

class PopularMoviesFragment : BaseFragment() ,
    MoviesAdapter.OnItemClickListener {

    companion object {
        fun newInstance() =
            PopularMoviesFragment()
    }
    private lateinit var moviesViewModel: PopularMoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun layoutID(): Int {
        return R.layout.popular_movies_fragment
    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        moviesViewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel::class.java)

        movie_list_rv.layoutManager=LinearLayoutManager(this.activity)
        moviesAdapter=
            MoviesAdapter(this)
        movie_list_rv.adapter=moviesAdapter

        moviesViewModel.requestMovies()
        moviesViewModel.getMovies().observe(viewLifecycleOwner,
            Observer<List<Result>> {
                moviesAdapter.setMoviesList(it)
                moviesAdapter.notifyDataSetChanged()

            })

        moviesViewModel.getIsLoading().observe(viewLifecycleOwner,object :Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
            if(moviesViewModel.getIsLoading().value==false){
                movies_list_progress_bar.visibility=View.GONE
                movie_list_rv.visibility=View.VISIBLE
            }
                else{
                movies_list_progress_bar.visibility=View.VISIBLE
                movie_list_rv.visibility=View.GONE
            }
            }
        })


    }

    override fun onItemClick(position: Int) {
        val args=Bundle()
        moviesViewModel.getMovies().value?.get(position)?.id?.let { args.putInt("movie_id", it) }
        val movieDetails=
            MovieDetailsFragment.newInstance()
        movieDetails.arguments=args
        (activity as MainActivity).replaceFragment(movieDetails,"movie_details")
    }
}