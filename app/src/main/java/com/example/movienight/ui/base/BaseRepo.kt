package com.example.movienight.ui.base

import androidx.lifecycle.MutableLiveData

open class BaseRepo {
    val requestErrorMessage = MutableLiveData<String>()
}