package com.example.yonetimerchant.fragments.tracking

import androidx.viewpager.widget.PagerAdapter
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ProgressOrdersAdapter
import com.example.yonetimerchant.databinding.FragmentProgressOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgressOrdersFragment :
    BaseFragment<OrdersTrackingViewModel,FragmentProgressOrdersBinding>() {
    private lateinit var orderTrackingAdapter: PagerAdapter

    override fun getLayout(): Int = R.layout.fragment_progress_orders

    override fun bindingToViews() {

    }

    override fun getViewMode(): Class<OrdersTrackingViewModel> = OrdersTrackingViewModel::class.java
}
