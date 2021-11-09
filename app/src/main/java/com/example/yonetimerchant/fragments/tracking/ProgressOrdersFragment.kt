package com.example.yonetimerchant.fragments.tracking

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ActiveOrdersAdapter
import com.example.yonetimerchant.databinding.FragmentProgressOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgressOrdersFragment :
    BaseFragment<OrderTrackingViewModel, FragmentProgressOrdersBinding>() {
    private lateinit var progressAdapter: ActiveOrdersAdapter
    private lateinit var orderTrackingAdapter: PagerAdapter

    override fun getLayout(): Int = R.layout.fragment_progress_orders
    private var PROGRESS_ORDER: String = "start"

    override fun bindingToViews() {
        viewModel!!.activeOrders(offset, pageSize,PROGRESS_ORDER)
        viewModel!!.activeOrdersList.observe(this, Observer { activeOrders ->
            if (activeOrders !=null && activeOrders.size != 0) {
                progressAdapter = ActiveOrdersAdapter(activeOrders,null,this)
                binding.rvProgressOrders.adapter = progressAdapter
            }
        })
    }
    fun openingDetails(orderId: String?) {
        var bundle = Bundle()
        bundle.putString(getString(R.string.order_id),orderId)
        findNavController().navigate(R.id.orderDetailsFragment)
    }
    override fun getViewMode(): Class<OrderTrackingViewModel> = OrderTrackingViewModel::class.java
}
