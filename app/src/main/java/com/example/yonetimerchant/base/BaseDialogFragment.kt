package com.example.yoneti.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseDialogFragment<V: ViewModel,T: ViewDataBinding>:DialogFragment() {
    private lateinit var jingaLala: AlertDialog
    var vieModel:V? = null
    lateinit var dataBinding:T
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),getLayout(),container,false)
//        return dataBinding.root
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
                dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),getLayout(),null,false)
        jingaLala =  AlertDialog.Builder(dataBinding.root.context).setView(dataBinding.root.rootView).create()
        bindingToView()
        super.onCreate(savedInstanceState)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),getLayout(),null,false)

        return jingaLala
    }


    abstract fun getLayout():Int

    abstract fun bindingToView()
}