package com.example.yonetimerchant.fragments.orders

import android.content.ContextWrapper
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ActiveOrdersAdapter
import com.example.yonetimerchant.adapters.CompletedOrdersAdapter
import com.example.yonetimerchant.databinding.FragmentListOfOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfOrdersFragment : BaseFragment<ListOfOrdersViewModel, FragmentListOfOrdersBinding>() {
    private lateinit var adapter: CompletedOrdersAdapter
    private lateinit var activeOrderdapter: ActiveOrdersAdapter
    private var offset: Int = 0
    private var limit: Int = 10
    override fun getViewMode(): Class<ListOfOrdersViewModel> = ListOfOrdersViewModel::class.java

    override fun getLayout(): Int = R.layout.fragment_list_of_orders
    override fun bindingToViews() {

        binding.listOfOrdersViewModel = viewModel

        viewModel!!.activeOrders(offset, limit)
        viewModel!!.completedOrders(offset, limit)

        setUi()


        binding.completedOrders.setOnClickListener {
            Toast.makeText(requireContext(), "Complete Order", Toast.LENGTH_SHORT).show()
            onCompleteOrderSelected()
            binding.rvActiveOrders.visibility = View.GONE
            binding.rvOrders.visibility = View.VISIBLE
        }

        binding.activeOrders.setOnClickListener {
            Toast.makeText(requireContext(), "active Order", Toast.LENGTH_SHORT).show()
            onActiveOrderSelected()
            binding.rvOrders.visibility = View.GONE
            binding.rvActiveOrders.visibility = View.VISIBLE
        }

        binding.ivSearchOrder.setOnClickListener {
            Toast.makeText(
                requireContext(), "searching", Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun onCompleteOrderSelected() {
        binding.tvOrderStatus.setText(resources.getString(R.string.complete_orders))

        binding.completedOrders.setBackgroundResource(R.drawable.circular_background)
        binding.ivCompleteOrders.setBackgroundResource(R.drawable.ic_check_white)
        binding.tvCompletedOrdersNumbers.setTextColor(Color.WHITE)
        binding.tvcompletedOrdersStatus.setTextColor(Color.WHITE)

        binding.activeOrders.setBackgroundResource(R.drawable.unselected_circular_background)
        binding.ivActiveOrders.setBackgroundResource(R.drawable.ic_solid_check)
        binding.tvActiveOrdersNumber.setTextColor(Color.parseColor("#4b6057"))
        binding.tvActiveOrderHeadline.setTextColor(Color.parseColor("#4b6057"))


    }

    private fun onActiveOrderSelected() {
        binding.tvOrderStatus.setText(resources.getString(R.string.active_orders))
        binding.activeOrders.setBackgroundResource(R.drawable.circular_background)
        binding.ivActiveOrders.setBackgroundResource(R.drawable.ic_check_white)
        binding.tvActiveOrdersNumber.setTextColor(Color.WHITE)
        binding.tvActiveOrderHeadline.setTextColor(Color.WHITE)

        binding.completedOrders.setBackgroundResource(R.drawable.unselected_circular_background)
        binding.ivCompleteOrders.setBackgroundResource(R.drawable.ic_solid_check)
        binding.tvCompletedOrdersNumbers.setTextColor(Color.parseColor("#4b6057"))
        binding.tvcompletedOrdersStatus.setTextColor(Color.parseColor("#4b6057"))

    }

    private fun setUi() {
        binding.ivCompleteOrders.setBackgroundResource(R.drawable.ic_check_white)
        binding.ivActiveOrders.setBackgroundResource(R.drawable.ic_solid_check)
        viewModel!!.listOfOrderErrors.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel!!.activeOrdersList.observe(this, Observer {
            binding.tvActiveOrdersNumber.text = it.size.toString()
            if (it.size != 0) {
                activeOrderdapter = ActiveOrdersAdapter(it)
                binding.rvActiveOrders.adapter = activeOrderdapter
            }
        })

        viewModel!!.completeOrdersList.observe(this, Observer {
            binding.tvCompletedOrdersNumbers.text = it.size.toString()
            if (it.size != 0) {
                adapter = CompletedOrdersAdapter(
                    it,
                    ListOfOrdersFragment@this,
                    null
                )
                binding.rvOrders.adapter = adapter
            }
        })
    }

    fun openOrderDetail() {
        findNavController().navigate(R.id.orderDetailsFragment)
    }
}