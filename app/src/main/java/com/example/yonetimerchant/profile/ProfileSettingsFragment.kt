package com.example.yonetimerchant.profile

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.yoneti.base.BaseFragment
import com.example.yoneti.base.BaseResult
import com.example.yonetimerchant.R
import com.example.yonetimerchant.acitivities.HomeActivity
import com.example.yonetimerchant.databinding.FragmentProfileOptionsRevisedBinding
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileSettingsFragment : BaseFragment<MeViewModel, FragmentProfileOptionsRevisedBinding>() {
    @Inject
    lateinit var preferences: MyPreferences
    @Inject
    lateinit var gson:Gson

    override fun getLayout() = R.layout.fragment_profile_options_revised

    override fun bindingToViews() {
        binding.meViewModel = viewModel

        binding.tvChangePassword.setOnClickListener {
            findNavController().navigate(R.id.changePasswordDialogFragment)
        }
        binding.tvMoreSettigs.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }
        binding.tvAboutYoneti.setOnClickListener {
            findNavController().navigate(R.id.aboutYonetiFragment)
        }
        binding.tvFeedbackSupport.setOnClickListener {
            findNavController().navigate(R.id.feedbackSupportFragment)
        }
        binding.tvMyReviews.setOnClickListener {
//            findNavController().navigate(R.id.profileFragment3)
        }
        binding.tvSignout.setOnClickListener{
//        findNavController().navigate(R.id.profileFragment3)
        }

        viewModel!!.isLogout.observe(this, Observer {
            if (it)
                (activity as HomeActivity).signOutclick()
        })

        loadingImages(gson.fromJson(preferences.getUser(),BaseResult::class.java))
    }

    private fun loadingImages(profileData: BaseResult) {
        Glide.with(requireContext())
            .load(profileData.coverPhoto)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.yoneti_test_profile)
            .into(binding.ivUserImageAvatar)
//        Glide.with(requireContext())
//            .load(profileData.avatar)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .into(binding.ivUserAvatar)
    }

    override fun getViewMode(): Class<MeViewModel> = MeViewModel::class.java

}