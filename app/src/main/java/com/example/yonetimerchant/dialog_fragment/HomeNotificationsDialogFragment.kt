package com.example.yonetimerchant.dialog_fragment

import androidx.fragment.app.Fragment
import com.example.yoneti.base.SharedBaseTestDialogFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.NotificationPagerAdapter
import com.example.yonetimerchant.databinding.DialogFragmentNotificationsBinding
import com.example.yonetimerchant.fragments.notifications.TimerFragment
import com.example.yonetimerchant.fragments.notifications.NotificationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeNotificationsDialogFragment :SharedBaseTestDialogFragment<DialogFragmentNotificationsBinding>(){
    var listOfFragments =  mutableListOf<Fragment>()
    var tabsTitle = mutableListOf<String>()
    lateinit var notificationAdapter: NotificationPagerAdapter
    init {
        tabsTitle.add("Messages")
        tabsTitle.add("Alerts")
        listOfFragments.add(TimerFragment())
        listOfFragments.add(NotificationFragment())
    }
    override fun getLayout(): Int  = R.layout.dialog_fragment_notifications

    override fun bindingToView() {

        notificationAdapter = NotificationPagerAdapter(childFragmentManager,listOfFragments)
dataBinding.notificationsPager.adapter = notificationAdapter
        dataBinding.notificationTab.setupWithViewPager(dataBinding.notificationsPager)
        dataBinding.notificationTab.getTabAt(0)?.text = tabsTitle.get(0)
        dataBinding.notificationTab.getTabAt(1)?.text = tabsTitle.get(1)
    }

}