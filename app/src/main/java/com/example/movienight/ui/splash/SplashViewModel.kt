package com.example.movienight.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.os.Handler;
import com.example.movienight.data.repository.SplashActivityRepo
import com.example.movienight.ui.base.BaseViewModel

class SplashViewModel(splashActivityRepo: SplashActivityRepo) : BaseViewModel<SplashActivityRepo>(splashActivityRepo) {
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