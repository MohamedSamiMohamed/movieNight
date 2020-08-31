package com.example.movienight

import com.example.movienight.data.repository.MainActivityRepo
import com.example.movienight.data.repository.MovieDetailsRepo
import com.example.movienight.data.repository.PopularMoviesRepo
import com.example.movienight.data.repository.SplashActivityRepo
import com.example.movienight.ui.main.MainViewModel
import com.example.movienight.ui.movieDetails.MovieDetailsViewModel
import com.example.movienight.ui.popularMovies.PopularMoviesViewModel
import com.example.movienight.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val movieDetailsViewModelModule:Module= module {
    factory { MovieDetailsRepo() }
    viewModel { MovieDetailsViewModel(get()) }
}

val popularMoviesViewModelModule: Module = module {
    factory { PopularMoviesRepo() }
    viewModel { PopularMoviesViewModel(get()) }
}

val splashViewModelModule:Module= module {
    factory { SplashActivityRepo() }
    viewModel { SplashViewModel(get()) }
}

val mainViewModel:Module= module {
    factory { MainActivityRepo() }
    viewModel { MainViewModel(get()) }
}
