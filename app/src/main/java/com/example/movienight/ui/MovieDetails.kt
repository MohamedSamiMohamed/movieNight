package com.example.movienight.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.movienight.R
import kotlin.properties.Delegates

 class MovieDetails : Fragment() {

    companion object {
        fun newInstance() = MovieDetails()

    }

    private lateinit var viewModel: MovieDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.movie_details_fragment, container, false)
        var movieID= arguments?.getInt("movie_id")
        val textView=view.findViewById<TextView>(R.id.test)
        textView.text=movieID.toString()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}