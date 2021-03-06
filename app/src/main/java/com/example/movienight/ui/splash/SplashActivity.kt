package com.example.movienight.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.movienight.R
import com.example.movienight.ui.main.MainActivity
import com.example.movienight.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewModel>() {
    private val splashViewModel:SplashViewModel by viewModel()
    override fun layoutID(): Int {
        return R.layout.activity_splash_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun getViewModel(): SplashViewModel = splashViewModel

}

