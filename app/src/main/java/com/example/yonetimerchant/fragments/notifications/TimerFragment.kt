package com.example.yonetimerchant.fragments.notifications

import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.MessagesAdapter
import com.example.yonetimerchant.databinding.FragmentNotificationsBinding
import com.example.yonetimerchant.databinding.FragmentTimerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TimerFragment : BaseFragment<MessageNotificationsViewModel, FragmentTimerBinding>() {

    private lateinit var adapter: MessagesAdapter
    var pageOffset = 0
    override fun getViewMode(): Class<MessageNotificationsViewModel> =
        MessageNotificationsViewModel::class.java

    override fun getLayout(): Int = R.layout.fragment_timer

    override fun bindingToViews() {
        viewModel!!.getMessagesNotificationList(pageOffset)
        viewModel!!.messagesList.observe(this, Observer {
            adapter = MessagesAdapter(it, MessagesFragment@ this)
            binding.rvNotifications.adapter = adapter
        })

        viewModel!!.message.observe(this, Observer { message->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })
//        binding.timepicker.setIs24HourView(true)
    }

}