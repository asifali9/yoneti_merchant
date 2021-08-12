package com.example.yoneti.base

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment


abstract class SharedBaseTestDialogFragment<T : ViewDataBinding>: DialogFragment() {
    lateinit var dataBinding:T
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            getLayout(),
            null,
            false
        )
        bindingToView()
        var dialog =  AlertDialog.Builder(dataBinding.root.context).setView(dataBinding.root.rootView).create()
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val window: Window? = dialog!!.window

        // set "origin" to top left corner, so to speak

        // set "origin" to top left corner, so to speak
        window!!.setGravity(Gravity.TOP or Gravity.LEFT)

        // after that, setting values for x and y works "naturally"

        // after that, setting values for x and y works "naturally"
        val params: WindowManager.LayoutParams = window.getAttributes()
        params.x = 300
        params.y = 100
        window!!.setAttributes(params)

        return dataBinding.root.rootView
    }

    abstract fun getLayout():Int

    abstract fun bindingToView()
}