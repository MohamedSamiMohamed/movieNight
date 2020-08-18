package com.example.movienight.ui.utilities
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.example.movienight.R

abstract class BaseActivity<T:BaseViewModel<*>>:AppCompatActivity() {

    abstract fun layoutID():Int
    abstract fun getViewModel():T
    protected lateinit var mViewModel:T
    lateinit var  dialog : MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutID())
        dialog= MaterialDialog(this).noAutoDismiss()
            .customView(R.layout.loading_dialog)
        mViewModel = ViewModelProviders
            .of(this)
            .get(getViewModel()::class.java)
        mViewModel.isLoading.observe(this, Observer {
            showLoadingDialog(it)
        })
        mViewModel.mRepo.requestErrorMessage.observe(this, Observer {
            showLoadingDialog(false)
            showToast(it)
        })
    }
    private fun showToast(errMessage: String) {
        Toast.makeText(this, errMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadingDialog(show: Boolean) {
        dialog.setCanceledOnTouchOutside(false)
        if (show) {
            dialog.show()

        } else {
            dialog.dismiss()
        }
    }
}