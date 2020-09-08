package com.example.movienight.ui.popularMovies.di

import com.example.movienight.ui.popularMovies.PopularMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val popularMoviesViewModelModule: Module = module {
    viewModel { PopularMoviesViewModel(get()) }
}
