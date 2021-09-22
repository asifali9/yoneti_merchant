package com.example.yonetimerchant.fragments.tracking

import androidx.viewpager.widget.PagerAdapter
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.CancelOrdersAdapter
import com.example.yonetimerchant.adapters.CancelOrdersViewHolder
import com.example.yonetimerchant.databinding.FragmentCancelOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CancelOrdersFragment :
    BaseFragment<OrdersTrackingViewModel, FragmentCancelOrdersBinding>() {

    private lateinit var cancelOrdersAdapter: CancelOrdersAdapter
    private lateinit var orderTrackingAdapter: PagerAdapter

    override fun getLayout(): Int = R.layout.fragment_cancel_orders

    override fun bindingToViews() {
        cancelOrdersAdapter = CancelOrdersAdapter(this)
binding.rvCancelOrders.adapter = cancelOrdersAdapter
    }

    override fun getViewMode(): Class<OrdersTrackingViewModel> = OrdersTrackingViewModel::class.java
}
