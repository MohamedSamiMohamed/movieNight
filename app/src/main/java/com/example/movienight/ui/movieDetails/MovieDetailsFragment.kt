package com.example.movienight.ui.movieDetails

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movienight.R
import com.example.movienight.databinding.MovieDetailsFragmentBinding
import com.example.movienight.ui.movieDetails.models.MovieDetailsUi
import com.example.movienight.ui.base.BaseFragment
import com.example.movienight.ui.base.Constants
import kotlinx.android.synthetic.main.movie_details_fragment.*
import kotlin.properties.Delegates

class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>() {

    companion object {
        fun newInstance() =
            MovieDetailsFragment()
    }

    private var movieID:Int?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding:MovieDetailsFragmentBinding=DataBindingUtil.setContentView(requireActivity(),R.layout.movie_details_fragment)
        movieID = arguments?.getInt(Constants.MOVIE_ID)!!
        mViewModel.movieID = movieID
        mViewModel.requestMovieDetails()
        mViewModel.movieDetailsUI.observe(viewLifecycleOwner, Observer<MovieDetailsUi> {
            binding.nestedScrollMovieDetails.visibility = View.VISIBLE
            binding.appBar.visibility = View.VISIBLE
            binding.movieDetails=it
        })
    }

    override fun layoutID(): Int {
        return R.layout.movie_details_fragment
    }

    override fun getViewModel(): MovieDetailsViewModel {
        return MovieDetailsViewModel()
    }


}