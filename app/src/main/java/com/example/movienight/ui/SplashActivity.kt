package com.example.movienight.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movienight.R
import com.example.movienight.ui.utilities.BaseActivity

class SplashActivity : BaseActivity() {
    private lateinit var splashViewModel: SplashViewModel
    override fun layoutID(): Int {
        return R.layout.activity_splash_screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel.startDelay()
        splashViewModel.endTime().observe(this, Observer {
            if (it) {
                val intent = Intent(
                    baseContext,
                    MainActivity::class.java
                )
                startActivity(intent)
            }
        })
    }

}

