package com.example.yonetimerchant.fragments.single_photo

import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentServiceProviderSinglePhotoBinding
import com.example.yonetimerchant.utils.Constants.MERCHANT_SELECTED_IMAGE

class MerchantSinglePhotoFragment :
    BaseFragment<MerchantSinglePhotoModel, FragmentServiceProviderSinglePhotoBinding>() {
    private lateinit var imageUrl: String
    private lateinit var navigation: NavController

    override fun getViewMode(): Class<MerchantSinglePhotoModel> =
        MerchantSinglePhotoModel::class.java

    override fun getLayout(): Int = R.layout.fragment_service_provider_single_photo

    override fun bindingToViews() {
//        navigation =  Navigation.findNavController(requireActivity(),R.id.service_viewer)

        imageUrl = arguments?.getString(MERCHANT_SELECTED_IMAGE)!!

        viewModel!!.imageAndCommentCount("iamgeId")
        binding.ivContextualMenu.setOnClickListener {
            findNavController().navigate(R.id.contextualMenuDialogFragment)
        }
        binding.tvLikes.setOnClickListener {
            findNavController().navigate(R.id.likesDialogFragment)
        }
        binding.ivChat.setOnClickListener {
            findNavController().navigate(R.id.photoCommentsDialogFragment)
        }
        Glide.with(requireContext())
            .load(imageUrl)
            .into(binding.ivMerchant)
        binding.ivSinglePhotoBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.ivLike.setOnClickListener {
            viewModel!!.isPhotoLiked("imageId")
        }


        setUi()
    }

    private fun setUi() {
    }
}