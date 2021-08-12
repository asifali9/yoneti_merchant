package com.example.yonetimerchant.dialog_fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseDialogFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentEditAlbumDialogBinding
import com.example.yonetimerchant.fragments.photos_fragment.BusinessPhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditAlbumDialogFragment(var updateTabName: (pos: Int) -> Unit,var currentItem: Int) :
    BaseDialogFragment<BusinessPhotosViewModel, FragmentEditAlbumDialogBinding>() {

    val photosAndAlbumViewModel: BusinessPhotosViewModel by viewModels({ requireParentFragment() })

    override fun getLayout(): Int = R.layout.fragment_edit_album_dialog

    override fun bindingToView() {
        dataBinding.businessPhotoViewModel = photosAndAlbumViewModel
        dataBinding.etoldname.setText(photosAndAlbumViewModel.selectedTabName)

        photosAndAlbumViewModel.albumNameUpdated.observe(this, Observer { isUpdated ->
            if (isUpdated)
            {
                updateTabName(currentItem)
                dismiss()
            }
        })
    }

}