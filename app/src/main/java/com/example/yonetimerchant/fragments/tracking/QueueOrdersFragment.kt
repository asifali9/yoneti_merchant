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
import com.example.yonetimerchant.adapters.QueueOrdersAdapter
import com.example.yonetimerchant.adapters.ServicePhotosGridPagerAdapter
import com.example.yonetimerchant.databinding.CustomTabTitleBinding
import com.example.yonetimerchant.databinding.FragmentOrdersTrackingBinding
import com.example.yonetimerchant.databinding.FragmentQueueOrdersBinding
import com.example.yonetimerchant.dialog_fragment.EditAlbumDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QueueOrdersFragment :
    BaseFragment<OrderTrackingViewModel, FragmentQueueOrdersBinding>() {

    private lateinit var queueOrdersAdapter: QueueOrdersAdapter
    private lateinit var orderTrackingAdapter: PagerAdapter

    override fun getLayout(): Int = R.layout.fragment_queue_orders
    override fun bindingToViews() {

        queueOrdersAdapter = QueueOrdersAdapter(this)
        binding.rvQueueOrders.adapter = queueOrdersAdapter
    }

    override fun getViewMode(): Class<OrderTrackingViewModel> = OrderTrackingViewModel::class.java
}
