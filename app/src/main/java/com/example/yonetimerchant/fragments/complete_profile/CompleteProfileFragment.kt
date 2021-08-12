package com.example.yonetimerchant.fragments.complete_profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.CompletedOrdersAdapter
import com.example.yonetimerchant.adapters.PendingOrderAdapter
import com.example.yonetimerchant.databinding.FragmentCompleteProfileBinding
import com.example.yonetimerchant.model.QueueOrders
import com.example.yonetimerchant.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CompleteProfileFragment : BaseFragment<ProfileViewModel, FragmentCompleteProfileBinding>() {
    private lateinit var adapter: PendingOrderAdapter
    private lateinit var completedOrders: CompletedOrdersAdapter

    var pageNumber:Int = 0
    var pageSize:Int = 10
    override fun getViewMode(): Class<ProfileViewModel> = ProfileViewModel::class.java

    override fun getLayout(): Int {
        return R.layout.fragment_complete_profile
    }

    override fun bindingToViews() {

        viewModel!!.isProfileCompleted()

        viewModel!!.isProfileCompleted.observe(this, Observer { isCompleted ->
            if (isCompleted) {
                viewModel!!.getDashBoard(pageNumber,pageSize)
                binding.dashboardRoot.visibility = View.VISIBLE
                binding.completeProfileRoot.visibility = View.GONE
            } else {
                binding.dashboardRoot.visibility = View.GONE
                binding.completeProfileRoot.visibility = View.VISIBLE
            }
        })

        viewModel!!.dashBoard.observe(this, Observer { dashboardResponse ->

            binding.tvCompletedOrderscount.setText(dashboardResponse.result.completeOrdersCount.toString())
            binding.tvServiceDistance.setText(dashboardResponse.result.serviceRadius)

            if(dashboardResponse.result.queueOrdersList != null && dashboardResponse.result.queueOrdersList.size > 0)
            {
                binding.tvQueueOrdersCount.setText(dashboardResponse.result.queueOrdersList.size.toString())
                adapter = PendingOrderAdapter(dashboardResponse.result.queueOrdersList,CompleteProfileFragment@this)
                binding.rvBookingInQueues.adapter = adapter
            }
            if (dashboardResponse.result.recentOrdersList != null && dashboardResponse.result.recentOrdersList.size > 0 ) {
                binding.tvQueueOrdersCount.setText(dashboardResponse.result.recentOrdersList.size.toString())
                completedOrders = CompletedOrdersAdapter(
                    dashboardResponse.result.recentOrdersList,
                    null,
                    CompleteProfileFragment@this
                )
                binding.rvRecentOrders.adapter = completedOrders
            }
        })

        binding.raySpeedometer.speedTo(50, 2000)

        binding.tvProgressCount.setText(binding.raySpeedometer.speed.toString())

        clickListeners()
    }

    private fun clickListeners() {
        binding.btnCompleteProfile.setOnClickListener {
            findNavController().navigate(R.id.profileSettingsFragment)
        }


        binding.ivNotification.setOnClickListener {

        }

        binding.tvSeeAllPendingOrders.setOnClickListener {

        }

        binding.tvSeeAllRecentOrders.setOnClickListener {

        }

        binding.completedOrdersContainer.setOnClickListener {
            findNavController().navigate(R.id.listOrdersNav)
        }

        binding.serviceArea.setOnClickListener {
            findNavController().navigate(R.id.discovery)

        }
    }

    fun openOrderDetail() {
        findNavController().navigate(R.id.orderDetailsFragment)
    }

    fun openPendingOrder(pendingOrder: QueueOrders) {
        var b = Bundle()
        b.putString("orderId",pendingOrder.orderId)
        findNavController().navigate(R.id.orderDetailsFragment,b)
    }
}