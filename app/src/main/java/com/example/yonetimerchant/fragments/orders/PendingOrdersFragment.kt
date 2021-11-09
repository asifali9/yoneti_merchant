package com.example.yonetimerchant.fragments.orders

import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentPendingOrdersBinding

class PendingOrdersFragment : BaseFragment<PendingOrdersViewModel,FragmentPendingOrdersBinding>() {

    override fun getViewMode(): Class<PendingOrdersViewModel> {
        return PendingOrdersViewModel::class.java
    }

    override fun getLayout(): Int {
        return R.layout.fragment_pending_orders
    }

    override fun bindingToViews() {
        viewModel!!.pendingOrders(offset,pageSize)

        viewModel!!.pendingOrdersList.observe(this, Observer { pendingOrdersList ->
            if (pendingOrdersList.size > 0 )
            {

            }
        })
    }
}