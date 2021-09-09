package com.example.yonetimerchant.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.yoneti.base.BaseFragment
import com.example.yoneti.model.Profile
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentProfileRevisedBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileRevisedBinding>() {
    private val SELECT_IMAGE: Int = 200
    private val COVER_REQUEST_CODE: Int = 300
    override fun getLayout() = R.layout.fragment_profile_revised

    override fun bindingToViews() {
        binding.proflieViewModel = viewModel
        binding.ivUpdateUserImage.setOnClickListener {
            openGallery(SELECT_IMAGE)

        }

        binding.btnUpdateCover.setOnClickListener {
            openGallery(COVER_REQUEST_CODE)
        }
        binding.ivChangeEmail.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("oldEmail", binding.etEmail.text.toString())
            findNavController().navigate(R.id.changeEmailDialogFragment)
        }

        binding.ivChangePhone.setOnClickListener {

            var bundle = Bundle()

            bundle.putString("oldPhone", binding.etPhone.text.toString())

            findNavController().navigate(R.id.changePhoneDialogFragment, bundle)
        }

        binding.ivChangeAddress.setOnClickListener {
            findNavController().navigate(R.id.changeAddressDialogFragment)
        }
        /**
         * bringData for profile
         */
        viewModel!!.getProfile()
        viewModel!!.profileData.observe(this, Observer {
            if (it.status) {
                updateUi(it)
            } else
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun updateUi(profile: Profile?) {
        profile!!.result.also {
            binding.etFullName.setText(it.fullname)
            binding.tvFullName.setText(it.fullname)
            binding.ilYourBio.setText(it.userBio)
            binding.tvBio.setText(it.userBio)
            binding.etWebsite.setText(it.website)
            binding.etDob.setText(it.dob)
            binding.etPhone.setText(it.phoneNumber)
            binding.etEmail.setText(it.email)
            binding.etAddress.setText(it.address.city)
            Glide.with(requireContext())
                .load(it.coverPhoto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivCoverPhoto)
            Glide.with(requireContext())
                .load(it.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivUserAvatar)
            binding.etAddress.setText(it.address.city)
//            binding.etPhone.setText(it.gender)
        }
    }

    private fun openGallery(REQUEST_CODE: Int) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE)
    }

//    override fun getViewMode(): Class<ProfileViewModel> = ProfileViewModel::class.java

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        val bitmap =
                            MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, data.data)
                        binding.ivUserAvatar.setImageBitmap(bitmap)

                        viewModel!!.updateUserPhoto(requireContext().contentResolver.openInputStream(data.data!!))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(activity, "Canceled", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == COVER_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        val bitmap =
                            MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, data.data)
                        binding.ivCoverPhoto.setImageBitmap(bitmap)
                        viewModel!!.updateCoverPhoto(requireContext().contentResolver.openInputStream(data.data!!))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(activity, "Canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getViewMode(): Class<ProfileViewModel> = ProfileViewModel::class.java
}