package com.example.yonetimerchant.fragments.orders

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentActiveOrdersDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailsFragment :
    BaseFragment<OrderDetailsViewModel, FragmentActiveOrdersDetailsBinding>() {
    var pageNumber = 0
    var pageSize = 0
    override fun getViewMode(): Class<OrderDetailsViewModel> = OrderDetailsViewModel::class.java

    override fun getLayout(): Int = R.layout.fragment_active_orders_details

    override fun bindingToViews() {
//        viewModel!!.orderDetails(pageNumber,pageSize)
        var orderId = arguments?.getString("orderId")

        binding.btnStartOrder.setOnClickListener {
            viewModel!!.startOrder(orderId)
        }

        viewModel!!.isOrderStarted.observe(this, Observer {
            if (it)
                Toast.makeText(requireContext(), "started", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(requireContext(), "something wrong", Toast.LENGTH_SHORT).show()
        })
    }

    private fun setUi() {
    }
}