package com.example.movienight.ui

import androidx.lifecycle.ViewModel
import com.example.movienight.repository.MainActivityRepo
import com.example.movienight.ui.utilities.BaseViewModel

class MainViewModel :BaseViewModel<MainActivityRepo>() {
    override fun getRepo(): MainActivityRepo {
        return MainActivityRepo()
    }

}