package com.example.yonetimerchant.fragments.tracking

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.CancelOrdersAdapter
import com.example.yonetimerchant.databinding.FragmentCancelOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CancelOrdersFragment :
    BaseFragment<OrderTrackingViewModel, FragmentCancelOrdersBinding>() {


    private var CANCEL_ORDER: String = "cancel"
    private lateinit var cancelOrdersAdapter: CancelOrdersAdapter
    private lateinit var orderTrackingAdapter: PagerAdapter

    override fun getLayout(): Int = R.layout.fragment_cancel_orders

    override fun bindingToViews() {
        viewModel!!.cancelOrders(offset,pageSize,CANCEL_ORDER)
        viewModel!!.cancelOrdersList.observe(this, Observer {   list ->
            if (list !=null && list.size != 0) {
                cancelOrdersAdapter = CancelOrdersAdapter(this, list)
                binding.rvCancelOrders.adapter = cancelOrdersAdapter
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
