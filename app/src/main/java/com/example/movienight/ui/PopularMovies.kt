package com.example.movienight.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movienight.R
import com.example.movienight.models.movies.Result

class PopularMovies : Fragment() ,MoviesAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = PopularMovies()
    }
    private lateinit var moviesViewModel: PopularMoviesViewModel
    private lateinit var moviesAdapter:MoviesAdapter
    private lateinit var moviesRv:RecyclerView
    private lateinit var moviesProgressbar:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View= inflater.inflate(R.layout.popular_movies_fragment, container, false)
        moviesRv=view.findViewById<RecyclerView>(R.id.movie_list_rv)
        moviesProgressbar=view.findViewById(R.id.movies_list_progress_bar)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        moviesViewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel::class.java)
        initRecyclerView()
        moviesViewModel.requestMovies()
        moviesViewModel.getMovies().observe(viewLifecycleOwner,
            Observer<List<Result>> { moviesAdapter.notifyDataSetChanged()
            initRecyclerView()
            })

        moviesViewModel.getIsLoading().observe(viewLifecycleOwner,object :Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
            if(moviesViewModel.getIsLoading().value==false){
                moviesProgressbar.visibility=View.GONE
                moviesRv.visibility=View.VISIBLE
            }
                else{
                moviesProgressbar.visibility=View.VISIBLE
                moviesRv.visibility=View.GONE
            }
            }
        })


    }

    override fun onItemClick(position: Int) {
        val args=Bundle()
        moviesViewModel.getMovies().value?.get(position)?.id?.let { args.putInt("movie_id", it) }
        val movieDetails=MovieDetailsFragment.newInstance()
        movieDetails.arguments=args
        (activity as MainActivity).replaceFragment(movieDetails,"movie_details")
        //Toast.makeText(activity, "item ${position} is clicked", Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView(){
        moviesRv.layoutManager=LinearLayoutManager(this.activity)
        moviesAdapter= MoviesAdapter(this)
        moviesViewModel.getMovies().value?.let { moviesAdapter.setMoviesList(it) }
        Log.d("data",moviesViewModel.getMovies().value.toString())
        moviesRv.adapter=moviesAdapter
    }
}