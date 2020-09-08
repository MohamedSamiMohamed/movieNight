package com.example.movienight.ui.movieDetails.di

import com.example.movienight.ui.movieDetails.MovieDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val movieDetailsViewModelModule: Module = module {
    viewModel { MovieDetailsViewModel(get()) }
}