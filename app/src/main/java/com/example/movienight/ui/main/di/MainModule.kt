package com.example.movienight.ui.main.di

import com.example.movienight.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val mainViewModelModule: Module = module {
    viewModel { MainViewModel(get()) }
}
