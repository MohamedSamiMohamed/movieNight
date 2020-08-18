package com.example.movienight.ui.utilities

import androidx.lifecycle.MutableLiveData

open class BaseRepo {
    val requestErrorMessage = MutableLiveData<String>()
}