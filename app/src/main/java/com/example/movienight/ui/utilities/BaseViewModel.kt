package com.example.movienight.ui.utilities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseViewModel<R : BaseRepo> : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    var mRepo: R

    init {
        mRepo = getRepo()
    }

    abstract fun getRepo(): R
}