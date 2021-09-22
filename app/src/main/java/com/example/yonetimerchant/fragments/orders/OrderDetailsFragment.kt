package com.example.yonetimerchant.fragments.orders

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.acitivities.HomeActivity
import com.example.yonetimerchant.databinding.FragmentActiveOrdersDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailsFragment :
    BaseFragment<OrderDetailsViewModel, FragmentActiveOrdersDetailsBinding>() {
    var pageNumber = 0
    var pageSize = 10
    override fun getViewMode(): Class<OrderDetailsViewModel> = OrderDetailsViewModel::class.java

    override fun getLayout(): Int = R.layout.fragment_active_orders_details

    override fun bindingToViews() {
        binding.orderDetailsViewModel = viewModel
        viewModel!!.orderDetails(pageNumber,pageSize)
        var orderId = arguments?.getString("orderId")

        binding.btnStartOrder.setOnClickListener {
            viewModel!!.startOrder(orderId)
        }

        binding.btnCancelOrder.setOnClickListener {
            viewModel!!.cancelOrder(orderId)
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