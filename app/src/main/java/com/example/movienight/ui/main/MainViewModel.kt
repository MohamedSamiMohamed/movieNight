package com.example.movienight.ui.main

import com.example.movienight.data.repository.MainActivityRepo
import com.example.movienight.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<MainActivityRepo>() {
    override fun getRepo(): MainActivityRepo {
        return MainActivityRepo()
    }

}