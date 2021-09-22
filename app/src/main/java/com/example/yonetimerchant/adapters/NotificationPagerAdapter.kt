package com.example.yonetimerchant.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class NotificationPagerAdapter(
    fragmentManager: FragmentManager,
    var listOfFragments: MutableList<Fragment>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getCount(): Int {
        return listOfFragments.size
    }

    override fun getItem(position: Int): Fragment {
//        pass Data inside the constructor to show in recyclerview
        return listOfFragments.get(position)
    }
}