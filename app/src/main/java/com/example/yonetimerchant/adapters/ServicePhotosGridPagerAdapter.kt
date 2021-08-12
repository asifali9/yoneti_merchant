package com.example.yonetimerchant.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.yoneti.model.Album
import com.example.yonetimerchant.fragments.photos_fragment.GridPhotosFragment

class ServicePhotosGridPagerAdapter(fragmentManager: FragmentManager, var tabsCount: MutableList<Album>) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    companion object{
        var FRAGMENTS_NUM: Int = 1
    }

    override fun getCount(): Int {
        return tabsCount.size
    }

    override fun getItem(position: Int): Fragment {
//        pass Data inside the constructor to show in recyclerview
        return GridPhotosFragment(position,tabsCount.get(position).id)
    }
}