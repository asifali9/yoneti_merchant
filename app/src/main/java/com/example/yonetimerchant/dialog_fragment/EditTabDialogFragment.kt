package com.example.yonetimerchant.dialog_fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.base.SharedBaseDialogFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentChangeEmailDialogBinding
import com.example.yonetimerchant.databinding.FragmentEditTabDialogBinding
import com.example.yonetimerchant.fragments.photos_fragment.BusinessPhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTabDialogFragment :SharedBaseDialogFragment<FragmentEditTabDialogBinding>(){

    val vm:BusinessPhotosViewModel by viewModels ({requireParentFragment()})
    override fun getLayout(): Int = R.layout.fragment_edit_tab_dialog

    override fun bindingToView() {
        vm.selectedTabName
        dataBinding.tvCreateAlbum.setOnClickListener {
            CreateAlbumDialogFragment().show(childFragmentManager,"createAlbum")
//            findNavController().navigate(R.id.createAlbumDialogFragment)
        }
        dataBinding.tvEditAlbumName.setOnClickListener {
//            findNavController().navigate(R.id.editAlbumDialogFragment)
        }

    }
}