package com.example.movienight.ui.popularMovies

import android.util.Log
import androidx.lifecycle.*
import com.example.movienight.models.movies.Result
import com.example.movienight.repository.PopularMoviesRepo
import com.example.movienight.ui.uiModels.PopularMovieUi
import com.example.movienight.ui.utilities.BaseViewModel
import kotlinx.coroutines.launch


class PopularMoviesViewModel : BaseViewModel<PopularMoviesRepo>() {
    lateinit var movieListLive: LiveData<List<Result>>
    lateinit var movieListUI: LiveData<ArrayList<PopularMovieUi>>

    fun requestMovies() {
        isLoading.value = true
        movieListLive=mRepo.requestMovies()
        movieListUI=Transformations.switchMap(movieListLive){
            val popularMovies=ArrayList<PopularMovieUi>()

            for(i in 0..it.size-1){
                val popularMovieUiObject=PopularMovieUi()
                popularMovieUiObject.releaseDate=it[i].releaseDate
                popularMovieUiObject.title= it[i].title
                popularMovieUiObject.adult=it[i].adult
                popularMovieUiObject.posterPath=it[i].posterPath
                popularMovieUiObject.voteAverage=it[i].voteAverage
                popularMovieUiObject.overview=it[i].overview
                popularMovies.add(popularMovieUiObject)
            }
            isLoading.value=false
            MutableLiveData(popularMovies)
        }

    }


    override fun getRepo(): PopularMoviesRepo {
        return PopularMoviesRepo()
    }
}