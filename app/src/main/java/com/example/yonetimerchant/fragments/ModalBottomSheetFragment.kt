package com.example.yonetimerchant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.acitivities.HomeActivity
import com.example.yonetimerchant.databinding.FragmentProfileBottomSheetBinding
import com.example.yonetimerchant.databinding.FragmentServiceProviderSinglePhotoBinding
import com.example.yonetimerchant.fragments.single_photo.MerchantSinglePhotoModel
import com.example.yonetimerchant.profile.MeViewModel
import com.example.yonetimerchant.utils.Constants.MERCHANT_SELECTED_IMAGE
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ModalBottomSheetFragment :
    BottomSheetDialogFragment() {
//   val viewModel = ViewModelProvider(this).get(MeViewModel::class.java)

    private lateinit var bottomSheetBinding: FragmentProfileBottomSheetBinding
    private lateinit var imageUrl: String
    private lateinit var navigation: NavController
val viewModel:MeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomSheetBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_bottom_sheet,container,false)
//        bottomSheetBinding.meViewModel = viewModel

        bottomSheetBinding.meViewModel = viewModel
        bottomSheetBinding.tvChangePassword.setOnClickListener {
            findNavController().navigate(R.id.changePasswordDialogFragment)
        }
        bottomSheetBinding.tvMoreSettigs.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }
        bottomSheetBinding.tvAboutYoneti.setOnClickListener {
            findNavController().navigate(R.id.aboutYonetiFragment)
        }
        bottomSheetBinding.tvFeedbackSupport.setOnClickListener {
            findNavController().navigate(R.id.feedbackSupportFragment)
        }
        bottomSheetBinding.tvMyReviews.setOnClickListener {
//            findNavController().navigate(R.id.profileFragment3)
        }
        bottomSheetBinding.tvSignout.setOnClickListener{
//        findNavController().navigate(R.id.profileFragment3)
        }

        viewModel!!.isLogout.observe(this, Observer {
            if (it)
                (activity as HomeActivity).signOutclick()
        })

        return bottomSheetBinding.root
    }
}