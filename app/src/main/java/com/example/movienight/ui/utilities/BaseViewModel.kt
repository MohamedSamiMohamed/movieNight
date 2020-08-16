package com.example.movienight.ui.utilities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val isLoading=MutableLiveData<Boolean>(true)

}