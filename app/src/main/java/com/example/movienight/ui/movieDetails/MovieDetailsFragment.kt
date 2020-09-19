package com.example.movienight.ui.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.example.movienight.R
import com.example.movienight.constants.KeyConstants
import com.example.movienight.databinding.MovieDetailsFragmentBinding
import com.example.movienight.databinding.MovieRatingDialogBinding
import com.example.movienight.ui.base.BaseFragment
import com.example.movienight.ui.movieDetails.models.MovieDetailsUi
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailsFragment() :
    BaseFragment<MovieDetailsViewModel>() {

    companion object {
        fun newInstance() =
            MovieDetailsFragment()
    }
    private val movieDetailsViewModel:MovieDetailsViewModel by viewModel()
    lateinit var rateDialog: MaterialDialog
    private var movieID: Int? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: MovieDetailsFragmentBinding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.movie_details_fragment)
        binding.movieDetailsFragment = this
        binding.lifecycleOwner = this
        rateDialog = MaterialDialog(requireContext()).noAutoDismiss()
        movieID = arguments?.getInt(KeyConstants.MOVIE_ID)!!
        mViewModel.movieID = movieID
        mViewModel.requestMovieDetails()
        mViewModel.movieDetailsUI.observe(viewLifecycleOwner, Observer<MovieDetailsUi> {
            binding.movieDetails = it
        })

    }

    override fun layoutID(): Int {
        return R.layout.movie_details_fragment
    }

    override fun getViewModel(): MovieDetailsViewModel = movieDetailsViewModel

    fun rateMovieDialog() {
        val bindingRating: MovieRatingDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.movie_rating_dialog,
            null, false
        )
        rateDialog.setContentView(bindingRating.root)
        bindingRating.movieDetailsViewModel = mViewModel
        bindingRating.lifecycleOwner = this
        rateDialog.show()
        bindingRating.submitBtn.setOnClickListener {
            mViewModel.onRatingClick()
            successRatingObserver()
        }
    }

    private fun successRatingObserver() {
        mViewModel.successRating.observe(viewLifecycleOwner, Observer<Boolean> {
            if (it) {
                rateDialog.hide()
                Toast.makeText(context, context!!.getString(R.string.submit_rating_msg), Toast.LENGTH_SHORT).show()
            }
        })
    }

}