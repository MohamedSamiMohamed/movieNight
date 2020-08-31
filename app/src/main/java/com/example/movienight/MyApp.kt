package com.example.movienight

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            val modules = listOf(
                popularMoviesViewModelModule,
                movieDetailsViewModelModule,
                splashViewModelModule,
                mainViewModel
            )
            modules(modules)
        }
    }
}