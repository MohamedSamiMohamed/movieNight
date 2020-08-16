package com.example.movienight.ui.utilities

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.example.movienight.R

abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    protected lateinit var mViewModel: T
    abstract fun getViewModel(): T
    abstract fun layoutID(): Int
    lateinit var  dialog :MaterialDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(layoutID(), container, false)
        dialog= MaterialDialog( context!!).noAutoDismiss()
            .customView(R.layout.loading_dialog)
        mViewModel = ViewModelProviders
            .of(this)
            .get(getViewModel()::class.java)
        mViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            showLoadingDialog(it)
        })
        return view
    }

    private fun showLoadingDialog(show: Boolean) {
        Log.d("show Loading?",show.toString())
        dialog.setCanceledOnTouchOutside(false)
        if (show) {
            dialog.show()

        } else {
            dialog.dismiss()
        }
    }
}