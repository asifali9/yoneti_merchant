package com.example.yonetimerchant.fragments.tracking

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.CompleteOrdersAdapter
import com.example.yonetimerchant.databinding.FragmentCompleteOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompleteOrdersFragment :
    BaseFragment<OrderTrackingViewModel, FragmentCompleteOrdersBinding>() {
    private lateinit var orderTrackingAdapter: PagerAdapter

    override fun getLayout(): Int = R.layout.fragment_complete_orders
var COMPLETE_ORDER = "complete"
    override fun bindingToViews() {
        viewModel!!.completedOrders(offset,pageSize,COMPLETE_ORDER)
        viewModel!!.completeOrdersList.observe(this, Observer { activeOrders ->
            if (activeOrders != null && activeOrders.size != 0 )
            binding.rvCompleteOrders.adapter = CompleteOrdersAdapter(activeOrders,this)
        })
    }

    override fun getViewMode(): Class<OrderTrackingViewModel> = OrderTrackingViewModel::class.java
    fun openingDetails(orderId: String?) {
        var bundle = Bundle()
        bundle.putString(getString(R.string.order_id),orderId)
        findNavController().navigate(R.id.orderDetailsFragment)
    }
}
