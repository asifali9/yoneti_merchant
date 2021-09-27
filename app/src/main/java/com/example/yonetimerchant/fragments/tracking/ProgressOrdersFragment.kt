package com.example.yonetimerchant.fragments.tracking

import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ActiveOrdersAdapter
import com.example.yonetimerchant.adapters.ProgressOrdersAdapter
import com.example.yonetimerchant.databinding.FragmentProgressOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgressOrdersFragment :
    BaseFragment<OrderTrackingViewModel, FragmentProgressOrdersBinding>() {
    private lateinit var progressAdapter: ActiveOrdersAdapter
    private lateinit var orderTrackingAdapter: PagerAdapter

    var pageNumber = 0
    var pageSize = 10
    override fun getLayout(): Int = R.layout.fragment_progress_orders

    override fun bindingToViews() {
        viewModel!!.activeOrders(pageNumber, pageSize)
        viewModel!!.activeOrdersList.observe(this, Observer { activeOrders ->
            progressAdapter = ActiveOrdersAdapter(activeOrders)
            binding.rvProgressOrders.adapter = progressAdapter
        })
    }

    override fun getViewMode(): Class<OrderTrackingViewModel> = OrderTrackingViewModel::class.java
}
