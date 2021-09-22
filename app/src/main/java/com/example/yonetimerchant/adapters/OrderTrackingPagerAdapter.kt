package com.example.yonetimerchant.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class OrderTrackingPagerAdapter(
    fragmentManager: FragmentManager,
    var fragments: MutableList<Fragment>,
    var fragmentsTitle: ArrayList<String>
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentsTitle.get(position)
    }
}