package com.example.movienight.ui

import com.example.movienight.repository.MainActivityRepo
import com.example.movienight.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<MainActivityRepo>() {
    override fun getRepo(): MainActivityRepo {
        return MainActivityRepo()
    }

}