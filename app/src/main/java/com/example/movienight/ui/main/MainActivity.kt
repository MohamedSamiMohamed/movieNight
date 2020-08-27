package com.example.movienight.ui.main

import android.os.Bundle
import com.example.movienight.R
import com.example.movienight.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override fun layoutID(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel(): MainViewModel {
        return MainViewModel()
    }
}