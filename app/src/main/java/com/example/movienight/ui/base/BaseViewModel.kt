package com.example.movienight.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movienight.data.base.BaseRepo


abstract class BaseViewModel<R : BaseRepo> : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    var mRepo: R

    init {
        mRepo = getRepo()
    }

    abstract fun getRepo(): R
}