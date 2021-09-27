package com.example.yonetimerchant.fragments.tracking

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.OrderTrackingPagerAdapter
import com.example.yonetimerchant.databinding.FragmentOrdersTrackingBinding
import com.example.yonetimerchant.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersTrackingFragment :
    BaseFragment<OrderTrackingViewModel, FragmentOrdersTrackingBinding>() {

    private var fragmentsList = ArrayList<Fragment>()
    private var fragmentsTitle = ArrayList<String>()
    private lateinit var orderTrackingAdapter: PagerAdapter

    override fun getLayout(): Int = R.layout.fragment_orders_tracking

    override fun bindingToViews() {

        addingFragments()
        orderTrackingAdapter = OrderTrackingPagerAdapter(childFragmentManager, fragmentsList,fragmentsTitle)
        binding.ordersPager.adapter = orderTrackingAdapter
        binding.tabs.setupWithViewPager(binding.ordersPager)


        /**
         * setting page clicked position
         */
        var pageNUmber = arguments?.getInt(Constants.ORDER_PAGE) ?: 0
        binding.ordersPager.setCurrentItem(pageNUmber,true)
    }


    private fun addingFragments() {
        fragmentsList.add(ProgressOrdersFragment())
        fragmentsList.add(QueueOrdersFragment())
        fragmentsList.add(CancelOrdersFragment())
        fragmentsList.add(CompleteOrdersFragment())

        fragmentsTitle.add("Progress")
        fragmentsTitle.add("Queue")
        fragmentsTitle.add("Cancel")
        fragmentsTitle.add("Complete")
    }

    override fun getViewMode(): Class<OrderTrackingViewModel> = OrderTrackingViewModel::class.java
}
