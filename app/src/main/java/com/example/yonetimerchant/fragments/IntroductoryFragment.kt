package com.example.yonetimerchant.fragments

import com.example.yoneti.base.BaseFragment
import com.example.yoneti.model.IgnoreViewModel
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentIntroductoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroductoryFragment(val position: Int) : BaseFragment<IgnoreViewModel, FragmentIntroductoryBinding>() {
    override fun getLayout(): Int = R.layout.fragment_introductory

    override fun bindingToViews() {
        when (position) {
            0 -> {
                setViewResources(
                        title = getString(R.string.potential_leads),
                        description = getString(R.string.potential_leads_description),
                        descriptionLine2 = getString(R.string.potential_leads_description_line2),
                        imageResource = R.drawable.ic_quick_search,
                        0)
            }
            1 -> {
                setViewResources(
                    title = getString(R.string.search_for_business_location),
                    description = getString(R.string.search_for_place_description),
                    descriptionLine2 = getString(R.string.search_for_place_description_line2),
                    imageResource = R.drawable.ic_search_for_place,
                    pos = 1)
            }
            2 -> {
                setViewResources(
                    title = getString(R.string.schedule_clients),
                    description = getString(R.string.schedule_clients_description),
                    descriptionLine2 = getString(
                        R.string.schedule_clients_description_line2
                    ),
                    imageResource = R.drawable.ic_schedule_service, pos = 2
                )
            }
            3 -> {
                setViewResources(
                    title = getString(R.string.quick_access),
                    description = getString(R.string.quick_access_description),
                    descriptionLine2 = getString(
                        R.string.quick_access_description_line2
                    ),
                    imageResource = R.drawable.ic_quick_access, pos = 3
                )
            }
        }
    }

    fun setViewResources(
        title: String,
        description: String,
        descriptionLine2: String,
        imageResource: Int,
        pos: Int
    ) {
        binding.tvHeading.text = title
        binding.tvDescription.text = description
        binding.tvDescription2.text = descriptionLine2
        binding.ivAvatar.setImageResource(imageResource)
    }

    override fun getViewMode(): Class<IgnoreViewModel> = IgnoreViewModel::class.java


}