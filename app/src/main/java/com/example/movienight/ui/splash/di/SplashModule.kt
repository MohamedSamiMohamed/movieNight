package com.example.movienight.ui.splash.di

import com.example.movienight.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val splashViewModelModule: Module = module {
    viewModel { SplashViewModel(get()) }
}
