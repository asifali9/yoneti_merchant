package com.example.yonetimerchant.fragments.tracking

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.QueueOrdersAdapter
import com.example.yonetimerchant.databinding.FragmentQueueOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QueueOrdersFragment :
    BaseFragment<OrderTrackingViewModel, FragmentQueueOrdersBinding>() {
    private var QUEUE_ORDER: String = "pending"

    private lateinit var queueOrdersAdapter: QueueOrdersAdapter
    private lateinit var orderTrackingAdapter: PagerAdapter

    override fun getLayout(): Int = R.layout.fragment_queue_orders
    override fun bindingToViews() {
        viewModel!!.queueOrders(offset, pageSize, QUEUE_ORDER)
        viewModel!!.queueOrdersList.observe(this, Observer { list ->
            if (list != null && list.size != 0){
                queueOrdersAdapter = QueueOrdersAdapter(this, list)
            binding.rvQueueOrders.adapter = queueOrdersAdapter
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
