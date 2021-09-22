package com.example.yonetimerchant.fragments.complete_profile

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.CompletedOrdersAdapter
import com.example.yonetimerchant.adapters.PendingOrderAdapter
import com.example.yonetimerchant.adapters.ProgressOrdersAdapter
import com.example.yonetimerchant.databinding.FragmentCompleteProfileBinding
import com.example.yonetimerchant.dialog_fragment.HomeNotificationsDialogFragment
import com.example.yonetimerchant.model.QueueOrders
import com.example.yonetimerchant.profile.ProfileViewModel
import com.example.yonetimerchant.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class CompleteProfileFragment : BaseFragment<ProfileViewModel, FragmentCompleteProfileBinding>() {
    private lateinit var timer: CountDownTimer
    private lateinit var adapter: PendingOrderAdapter
    private lateinit var completedOrders: CompletedOrdersAdapter
    private lateinit var inProgressAdapter: ProgressOrdersAdapter

    var pageNumber: Int = 0
    var pageSize: Int = 10
    override fun getViewMode(): Class<ProfileViewModel> = ProfileViewModel::class.java

    override fun getLayout(): Int {
        return R.layout.fragment_complete_profile
    }

    override fun bindingToViews() {

        viewModel!!.isProfileCompleted()

        viewModel!!.isProfileCompleted.observe(this, Observer { isCompleted ->
            if (isCompleted) {
                viewModel!!.getDashBoard(pageNumber, pageSize)
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

            if (dashboardResponse.result.queueOrdersList != null && dashboardResponse.result.queueOrdersList.size > 0) {
                binding.tvQueueOrdersCount.setText(dashboardResponse.result.queueOrdersList.size.toString())
                var queOrders = if (dashboardResponse.result.queueOrdersList.size < 6)
                    dashboardResponse.result.queueOrdersList
                    else
                    dashboardResponse.result.queueOrdersList.subList(0,5)
                adapter = PendingOrderAdapter(
                    queOrders,
                    CompleteProfileFragment@ this
                )
                binding.rvBookingInQueues.adapter = adapter
            }
            if (dashboardResponse.result.recentOrdersList != null && dashboardResponse.result.recentOrdersList.size > 0) {

                var completeOrders = if (dashboardResponse.result.recentOrdersList.size < 6)
                    dashboardResponse.result.recentOrdersList
                else
                    dashboardResponse.result.recentOrdersList.subList(0,5)
                completedOrders = CompletedOrdersAdapter(
                    completeOrders,
                    null,
                    CompleteProfileFragment@ this
                )
                binding.rvRecentOrders.adapter = completedOrders
            }

            if (dashboardResponse.result.inProgressOrders != null && dashboardResponse.result.inProgressOrders.size > 0) {
                binding.tvInProgressOrdersCount.setText(dashboardResponse.result.inProgressOrders.size.toString())

                var inProgress = if (dashboardResponse.result.inProgressOrders.size < 6)
                    dashboardResponse.result.inProgressOrders
                else
                    dashboardResponse.result.inProgressOrders.subList(0,5)

                inProgressAdapter = ProgressOrdersAdapter(
                    inProgress,
                    CompleteProfileFragment@ this
                )
                binding.rvBookingInProgress.adapter = inProgressAdapter
            }

            jobTimer(dashboardResponse.result.bookingDueIn.toLong() * 60000)

        })

        binding.raySpeedometer.speedTo(50, 1500)

        binding.tvProgressCount.setText(binding.raySpeedometer.speed.toString())

        /**
         * coroutines testing
         */
        runBlocking {
            Log.d(TAG, "bindingToViews: ${Thread.currentThread().name}")
            Toast.makeText(requireContext(), "hello world", Toast.LENGTH_SHORT).show()
        }
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(requireContext(), "globalscope", Toast.LENGTH_SHORT).show()
        }

        /**
         * coroutines ending
         */
        clickListeners()
    }

    val TAG = CompleteProfileFragment::class.java.simpleName
    fun jobTimer(timeInMilis: Long) {
        /**
         * count down timer
         */
        timer = object : CountDownTimer(timeInMilis, 1000) {
            override fun onTick(p0: Long) {
                var minutes = p0 / 60000
                var seconds = (p0 / 1000) % 60

                var secondsStr = if (seconds.toString().length == 1) "0${seconds}" else seconds
                binding.tvCountdownTimer.setText(formatHoursAndMinutes(minutes.toInt()) + ":$secondsStr")
            }

            override fun onFinish() {
                viewModel!!.getNextJobTimer()
            }

        }
        if (timeInMilis != 0L)
            timer.start()
    }

    private fun clickListeners() {
        binding.btnCompleteProfile.setOnClickListener {
            findNavController().navigate(R.id.profileSettingsFragment)
        }


        binding.ivNotification.setOnClickListener {
            HomeNotificationsDialogFragment().show(childFragmentManager.beginTransaction(),"show")
        }

        binding.tvSeeAllPendingOrders.setOnClickListener {
            findNavController().navigate(R.id.listOrdersNav)
        }

        binding.tvSeeAllRecentOrders.setOnClickListener {
            findNavController().navigate(R.id.ordersTrackingFragment)
        }

        binding.completedOrdersContainer.setOnClickListener {
            findNavController().navigate(R.id.listOrdersNav)
        }

        binding.serviceArea.setOnClickListener {
            findNavController().navigate(R.id.discovery)

        }
        binding.tvSeeAllInProgressOrders.setOnClickListener {
//            var bundle = Bundle()
//            bundle.putInt(Constants.ORDER_PAGE,0)
            findNavController().navigate(R.id.ordersTrackingFragment)
        }

        binding.tvSeeAllRecentOrders.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt(Constants.ORDER_PAGE, 3)
            findNavController().navigate(R.id.ordersTrackingFragment, bundle)
        }
    }

    fun openOrderDetail() {
        findNavController().navigate(R.id.orderDetailsFragment)
    }

    fun openPendingOrder(pendingOrder: QueueOrders) {
        var b = Bundle()
        b.putString("orderId", pendingOrder.orderId)
        findNavController().navigate(R.id.orderDetailsFragment, b)
    }

    fun formatHoursAndMinutes(totalMinutes: Int): String {
        var minutes = Integer.toString(totalMinutes % 60)
        minutes = if (minutes.length == 1) "0$minutes" else minutes

        var hrs = Integer.toString(totalMinutes / 60)
        hrs = if (hrs.length == 1) "0$hrs" else hrs

        return "$hrs:$minutes"
    }
}