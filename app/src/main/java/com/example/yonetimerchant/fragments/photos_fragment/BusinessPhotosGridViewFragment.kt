package com.example.yonetimerchant.fragments.photos_fragment

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.yoneti.base.BaseFragment
import com.example.yoneti.model.Album
import com.example.yoneti.model.Profile
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ServicePhotosGridPagerAdapter
import com.example.yonetimerchant.databinding.CustomTabTitleBinding
import com.example.yonetimerchant.databinding.FragmentServicePhotosGridViewBinding
import com.example.yonetimerchant.dialog_fragment.EditAlbumDialogFragment
import com.example.yonetimerchant.fragments.ModalBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessPhotosGridViewFragment :
    BaseFragment<BusinessPhotosViewModel, FragmentServicePhotosGridViewBinding>() {
    private var tabTitle = mutableListOf<Album>()
    val TAG = BusinessPhotosGridViewFragment::class.java.name
    var albumIds = ArrayList<String>()
    private lateinit var gridPhotoAdapter: ServicePhotosGridPagerAdapter

    init {
        for (i in 0..3)
            tabTitle.add(Album("", "Tab $i", "", "0"))

    }

    override fun getLayout(): Int = R.layout.fragment_service_photos_grid_view

    override fun bindingToViews() {
        viewModel!!.getProfile()
        viewModel!!.getTabName()
        viewModel!!.tabsList.observe(this, Observer { tabsList ->
            if (tabsList.size != 0) {
                for (i in 0 until tabsList.size)
                    tabTitle.set(i, tabsList.get(i))
            }
            gridPhotoAdapter =
                ServicePhotosGridPagerAdapter(childFragmentManager, tabTitle)
            binding.photosPager.adapter = gridPhotoAdapter
            binding.servicePhotosTabs.setupWithViewPager(binding.photosPager)
            settingTitlesOnViewPager(tabTitle)
        })
//        also pass dataList to fragment to make tabs and viewPager dynamic
//        for (i in 0..tabTitle.size - 1) {
//            gridPhotoAdapter = ServicePhotosGridPagerAdapter(childFragmentManager)
//            binding.photosPager.adapter = gridPhotoAdapter
//            binding.servicePhotosTabs.setupWithViewPager(binding.photosPager)
//            settingTitlesOnViewPager(it)
//        }

        viewModel!!.profileData.observe(this, Observer {
            if (it.status) {
                updateUi(it)
            } else
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })
        binding.btnEditTab.setOnClickListener {
//            ServicePhotosGridPagerAdapter.FRAGMENTS_NUM =
//                ServicePhotosGridPagerAdapter.FRAGMENTS_NUM + 1
//            gridPhotoAdapter.notifyDataSetChanged()
            Toast.makeText(requireContext(), "show", Toast.LENGTH_SHORT).show()
            viewModel!!.selectedTabName = tabTitle.get(binding.photosPager.currentItem).albumName
            viewModel!!.selectedTabAlbumId = tabTitle.get(binding.photosPager.currentItem).id
            viewModel!!.selectedTabNumber = (binding.photosPager.currentItem)
//            findNavController().navigate(R.id.editTabDialogFragment)
            EditAlbumDialogFragment(::onUpdateTabName,binding.photosPager.currentItem).show(childFragmentManager,"editTab")
        }

        binding.btnEditProfile.setOnClickListener {
//            findNavController().navigate(R.id.profileSettingsFragment)
            ModalBottomSheetFragment().show(childFragmentManager,"show")
        }

    }

    private fun updateUi(profile: Profile?) {
        profile!!.result.also {
            binding.tvMerchantName.setText(it.fullname)
//            binding.tvAddress.setText(it.address.)
            binding.tvBio.setText(it.userBio)
            binding.tvWebsite.setText(it.website)
//
//            binding.tvCompleteAddress.setText(it.address.let {
//                it.street+it.city+it.country
//            })
            Glide.with(requireContext())
                .load(it.coverPhoto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.coverPhoto)
//            Glide.with(requireContext())
//                .load(it.avatar)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(binding.ivUserAvatar)
        }
    }


    fun onUpdateTabName(tabPosition:Int){
        viewModel!!.albumNameUpdated.value = false
//        binding.servicePhotosTabs.getTabAt(tabPosition)?.setText(viewModel!!.newAlbumName)!!
        var view = binding.servicePhotosTabs.getTabAt(tabPosition)!!.customView
        (view!!.findViewById<TextView>(R.id.tv_title)).setText(viewModel!!.newAlbumName)
    }
    private fun settingTitlesOnViewPager(albumList: MutableList<Album>) {
        for (i in 0..albumList.size - 1)
            binding.servicePhotosTabs.getTabAt(i)?.setCustomView(
                prepareTabView(
                    albumList.get(i).albumName,
                    albumList.get(i).tabCount
                )
            )
    }

    fun prepareTabView(tabTitle: String, tabCount: String): View {
        layoutInflater.inflate(R.layout.custom_tab_title, null)
        var tabBinding = CustomTabTitleBinding.inflate(LayoutInflater.from(context))
        tabBinding.tvTitle.text = tabTitle
        tabBinding.tvCount.text = "($tabCount)"
        return tabBinding.root
    }

    override fun getViewMode(): Class<BusinessPhotosViewModel> = BusinessPhotosViewModel::class.java
}
