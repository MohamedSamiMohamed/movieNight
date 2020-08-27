package com.example.movienight.data.base

import androidx.lifecycle.MutableLiveData

open class BaseRepo {
    val requestErrorMessage = MutableLiveData<String>()
}