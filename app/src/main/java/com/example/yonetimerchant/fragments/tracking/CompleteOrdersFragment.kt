package com.example.yonetimerchant.fragments.tracking

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.yoneti.base.BaseFragment
import com.example.yoneti.model.Album
import com.example.yoneti.model.Profile
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.CompleteOrdersAdapter
import com.example.yonetimerchant.adapters.ServicePhotosGridPagerAdapter
import com.example.yonetimerchant.databinding.CustomTabTitleBinding
import com.example.yonetimerchant.databinding.FragmentCompleteOrdersBinding
import com.example.yonetimerchant.databinding.FragmentOrdersTrackingBinding
import com.example.yonetimerchant.databinding.FragmentServicePhotosGridViewBinding
import com.example.yonetimerchant.dialog_fragment.EditAlbumDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompleteOrdersFragment :
    BaseFragment<OrdersTrackingViewModel, FragmentCompleteOrdersBinding>() {

    private lateinit var orderTrackingAdapter: PagerAdapter

    override fun getLayout(): Int = R.layout.fragment_complete_orders

    override fun bindingToViews() {
binding.rvCompleteOrders.adapter = CompleteOrdersAdapter(this)
    }

    override fun getViewMode(): Class<OrdersTrackingViewModel> = OrdersTrackingViewModel::class.java
}
