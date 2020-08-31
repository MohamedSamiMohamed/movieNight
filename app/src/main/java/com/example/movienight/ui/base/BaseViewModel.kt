package com.example.movienight.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movienight.data.base.BaseRepo
import com.example.movienight.data.repository.PopularMoviesRepo


abstract class BaseViewModel<R : BaseRepo>(val repo: R) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

}