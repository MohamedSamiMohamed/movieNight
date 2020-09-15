package com.example.movienight.data.di

import com.example.movienight.data.repository.*
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModule: Module = module {
    factory { MovieDetailsRepo() }
    factory { PopularMoviesRepo() }
    factory { SplashActivityRepo() }
    factory { MainActivityRepo() }
    factory { ChatRoomRepo() }
    factory { ChatUsersRepo() }
}