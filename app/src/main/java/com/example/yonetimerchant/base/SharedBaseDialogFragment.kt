package com.example.yoneti.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class SharedBaseDialogFragment<T: ViewDataBinding>: DialogFragment() {
    lateinit var dataBinding:T
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),getLayout(),null,false)
        bindingToView()
        var dialog =  AlertDialog.Builder(dataBinding.root.context).setView(dataBinding.root.rootView).create()
        return dialog
    }

    /**
     * for making round corners
     * just add a background drawable in GroupView
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun getLayout():Int

    abstract fun bindingToView()
}