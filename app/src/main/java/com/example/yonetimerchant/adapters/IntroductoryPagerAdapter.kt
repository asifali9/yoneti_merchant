package com.example.yonetimerchant.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.yonetimerchant.fragments.IntroductoryFragment

class IntroductoryPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val FRAGMENTS_NUM: Int = 4

    override fun getCount(): Int {
        return FRAGMENTS_NUM
    }

    override fun getItem(position: Int): Fragment {
        return IntroductoryFragment(position)
    }
}