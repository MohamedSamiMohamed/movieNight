package com.example.movienight.application

import android.app.Application
import com.example.movienight.data.di.repoModule
import com.example.movienight.ui.chatRoom.di.ChatRoomViewModelModule
import com.example.movienight.ui.main.di.mainViewModelModule
import com.example.movienight.ui.movieDetails.di.movieDetailsViewModelModule
import com.example.movienight.ui.popularMovies.di.popularMoviesViewModelModule
import com.example.movienight.ui.splash.di.splashViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            val modules = listOf(
                repoModule,
                popularMoviesViewModelModule,
                movieDetailsViewModelModule,
                splashViewModelModule,
                mainViewModelModule,
                ChatRoomViewModelModule
            )
            modules(modules)
        }
    }
}