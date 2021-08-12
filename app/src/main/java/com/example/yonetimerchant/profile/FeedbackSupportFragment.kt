package com.example.yonetimerchant.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentFeedbackSupportBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedbackSupportFragment : Fragment() {

    private lateinit var feedbackBinding: FragmentFeedbackSupportBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        feedbackBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback_support,container,false)
        bindViews()
        return feedbackBinding.root
    }

    private fun bindViews() {
        feedbackBinding.ivFeedBack.setOnClickListener {

        }
    }
}