package com.example.movienight.ui.popularMovies

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movienight.R
import com.example.movienight.data.repository.PopularMoviesRepo
import com.example.movienight.databinding.PopularMoviesFragmentBinding
import com.example.movienight.ui.base.BaseFragment
import com.example.movienight.ui.popularMovies.adapter.PopularMoviesAdapter
import com.example.movienight.utilities.Constants
import org.koin.android.viewmodel.ext.android.viewModel

class PopularMoviesFragment() : BaseFragment<PopularMoviesViewModel>(),
    PopularMoviesAdapter.OnItemClickListener {
    companion object {
        fun newInstance() =
            PopularMoviesFragment()
    }
    private val popularMoviesViewModel:PopularMoviesViewModel by viewModel()
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: PopularMoviesFragmentBinding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.popular_movies_fragment)
        navController = Navigation.findNavController(view)
        popularMoviesAdapter =
            PopularMoviesAdapter(this)
        binding.adapter = popularMoviesAdapter
        binding.chatButton.setOnClickListener {
            onChatClick()
        }
        mViewModel.requestMovies()
        mViewModel.movieListUI.observe(viewLifecycleOwner,
            Observer {
                popularMoviesAdapter.setMoviesList(it)
                popularMoviesAdapter.notifyDataSetChanged()
            })
    }

    override fun onItemClick(position: Int) {
        val args = bundleOf(Constants.MOVIE_ID to mViewModel.movieListUI.value?.get(position)?.id)
        navController.navigate(R.id.action_popularMoviesFragment_to_movieDetailsFragment, args)
    }

     private fun onChatClick(){
        navController.navigate(R.id.action_popularMoviesFragment_to_chatRoomFragment)
    }

    override fun layoutID(): Int {
        return R.layout.popular_movies_fragment
    }

    override fun getViewModel(): PopularMoviesViewModel = popularMoviesViewModel

}