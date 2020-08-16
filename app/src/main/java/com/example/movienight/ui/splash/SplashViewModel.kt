package com.example.movienight.ui.splash

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.os.Handler;
import com.example.movienight.ui.utilities.BaseViewModel

class SplashViewModel() : BaseViewModel() {
    val timeOut = MutableLiveData<Boolean>(false)

    fun startDelay() {
        val handler = Handler()
        handler.postDelayed(Runnable {
            timeOut.postValue(true)
        }, 3000)
    }

    fun endTime(): LiveData<Boolean> {
        return timeOut
    }

}