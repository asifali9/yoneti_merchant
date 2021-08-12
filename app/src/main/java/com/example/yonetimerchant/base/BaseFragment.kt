package com.example.yoneti.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


abstract class BaseFragment<V : ViewModel, T : ViewDataBinding> : Fragment() {

    var bundle: Bundle? = null
    lateinit var binding: T
    var viewModel: V? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!(this::binding.isInitialized)) {
            bundle = savedInstanceState
            binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
            viewModel = ViewModelProvider(this).get(getViewMode())
            bindingToViews()
        }
        return binding.root
    }

    abstract fun getViewMode(): Class<V>


    abstract fun getLayout(): Int

    abstract fun bindingToViews()
}