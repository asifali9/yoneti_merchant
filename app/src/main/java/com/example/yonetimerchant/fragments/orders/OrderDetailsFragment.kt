package com.example.yonetimerchant.fragments.orders

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.acitivities.HomeActivity
import com.example.yonetimerchant.adapters.OrderDetailsAdapter
import com.example.yonetimerchant.databinding.FragmentActiveOrdersDetailsBinding
import com.example.yonetimerchant.utils.showToast
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class OrderDetailsFragment :
    BaseFragment<OrderDetailsViewModel, FragmentActiveOrdersDetailsBinding>() {
    private lateinit var detailsAdapter: OrderDetailsAdapter
    override fun getViewMode(): Class<OrderDetailsViewModel> = OrderDetailsViewModel::class.java

    override fun getLayout(): Int = R.layout.fragment_active_orders_details

    override fun bindingToViews() {
        binding.orderDetailsViewModel = viewModel
        var orderId = arguments?.getString("orderId")
        viewModel!!.orderDetails(offset,pageSize,orderId!!)

        binding.btnStartOrder.setOnClickListener {
            viewModel!!.startOrder(orderId)
        }
        binding.btnCompleteOrder.setOnClickListener {
            viewModel!!.completeOrder(orderId)
        }

        binding.btnCancelOrder.setOnClickListener {
            viewModel!!.cancelOrder(orderId)
        }

        binding.btnRescheduleOrder.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .setTitleText("Select date")
                    .build()

            datePicker.show(childFragmentManager,"show")
            datePicker.addOnPositiveButtonClickListener {
                val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.setTimeInMillis(it)

                val format = SimpleDateFormat("yyyy-MM-dd")
                val formatted: String = format.format(calendar.getTime())

                Log.d("OrderDetails", "bindingToViews: $formatted")
                requireActivity().showToast(formatted)
                val timePicker = MaterialTimePicker.Builder()
                        .setTitleText(getString(R.string.order_detail_timepicker_title))
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .build()
                timePicker.show(childFragmentManager,"show")

                timePicker.addOnPositiveButtonClickListener {
                    var time  = timePicker.hour.toString()+timePicker.minute
                    requireActivity().showToast(time)
                }

//                viewModel!!.reschedudleOrders()
            }

        }

        viewModel!!.isOrderStarted.observe(this, Observer {
            if (it)
            {
                Toast.makeText(requireContext(), "started", Toast.LENGTH_SHORT).show()
                binding.btnStartOrder.visibility = View.GONE
                binding.btnCancelOrder.visibility = View.GONE
                binding.btnRescheduleOrder.visibility = View.GONE
                binding.btnCompleteOrder.visibility = View.VISIBLE
            }
            else
                Toast.makeText(requireContext(), "something wrong", Toast.LENGTH_SHORT).show()
        })


        viewModel!!.isOrderCompleted.observe(this, Observer {
            if (it){
                Toast.makeText(requireContext(), "Order Completed", Toast.LENGTH_SHORT).show()
                binding.btnCompleteOrder.visibility = View.GONE
            }
            else
                Toast.makeText(requireContext(), "something wrong", Toast.LENGTH_SHORT).show()
        })

        viewModel!!.details.observe(this, Observer { baseResult ->

            if (baseResult.orderStatus.equals("pending"))
            {
                binding.buttonGroup.visibility = View.VISIBLE
                binding.btnCompleteOrder.visibility = View.GONE
            }else if (baseResult.orderStatus.equals("start"))
            {
                binding.buttonGroup.visibility = View.GONE
                binding.btnCompleteOrder.visibility = View.VISIBLE
            }
            else if (baseResult.orderStatus.equals("cancel"))
            {
                binding.buttonGroup.visibility = View.GONE
                binding.btnCompleteOrder.visibility = View.GONE
                binding.tvOrderStatus.visibility = View.VISIBLE
                binding.tvOrderStatus.text = getString(R.string.order_cancelled)
            }
            else if (baseResult.orderStatus.equals("complete"))
            {
                binding.buttonGroup.visibility = View.GONE
                binding.btnCompleteOrder.visibility = View.GONE
                binding.tvOrderStatus.visibility = View.VISIBLE
                binding.tvOrderStatus.text = "waiting response from api"

            }
            Glide.with(requireContext())
                .load(baseResult.coverPhoto)
                .placeholder(R.drawable.img_profile_placeholder)
                .into(binding.activeOrderMapView)

            Glide.with(requireContext())
                .load(baseResult.userPhoto)
                .placeholder(R.drawable.img_profile_placeholder)
                .into(binding.imageView2)
            binding.tvUserName.text = baseResult.Username
            binding.tvService.text = "No service in api"
            binding.tvEmail.text = baseResult.userEmail
            binding.tvPhone.text = baseResult.phoneNumber
            binding.tvUserServiceCounter.text = baseResult.serviceCount.toString()
            binding.tvDateTime.text = baseResult.date+"\n"+baseResult.startTime



            if (baseResult.orderDetails != null && baseResult.orderDetails.size != 0) {
                binding.tvServiceListCounter.text = baseResult.orderDetails.size.toString()
                detailsAdapter = OrderDetailsAdapter(baseResult.orderDetails)
                binding.rvServiceDetails.adapter = detailsAdapter
            }
        })
    }

    private fun setUi() {
    }
}