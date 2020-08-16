package com.example.movienight.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movienight.R
import com.example.movienight.ui.MainActivity
import com.example.movienight.ui.utilities.BaseActivity

class SplashActivity : BaseActivity<SplashViewModel>() {
    override fun layoutID(): Int {
        return R.layout.activity_splash_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.isLoading.postValue(false)
        mViewModel.startDelay()
        mViewModel.endTime().observe(this, Observer {
            if (it) {
                val intent = Intent(
                    baseContext,
                    MainActivity::class.java
                )
                startActivity(intent)
            }
        })
    }

    override fun getViewModel(): SplashViewModel {
        return SplashViewModel()
    }

}

