package com.example.yonetimerchant.fragments.notifications

import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseFragment
import com.example.yoneti.model.Notifications
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.NotificationAdapter
import com.example.yonetimerchant.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment<NotificationsViewModel, FragmentNotificationsBinding>() {
    lateinit var notificationAdapter:NotificationAdapter
    var notificationsList = mutableListOf<Notifications>()
    override fun getViewMode(): Class<NotificationsViewModel> {
        return NotificationsViewModel::class.java
    }

    override fun getLayout(): Int = R.layout.fragment_notifications

    override fun bindingToViews() {
        viewModel!!.getAlertsList(0)

        viewModel!!.alerts.observe(this, Observer {
            notificationsList = it
            notificationAdapter = NotificationAdapter(notificationsList,this)
            binding.rvNotifications.adapter = notificationAdapter
        })

        viewModel!!.message.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

    }

    fun acceptOrRejectSwap(swapStatus: Boolean, notificationId: String) {
        viewModel!!.acceptOrReject(swapStatus,notificationId)
    }

}