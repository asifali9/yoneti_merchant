package com.example.yonetimerchant.dialog_fragment

import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.model.Countries
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ChangeAddressAdapter
import com.example.yonetimerchant.databinding.FragmentChangeAddressDialogBinding
import com.example.yonetimerchant.databinding.FragmentCreateAlbumDialogBinding
import com.example.yonetimerchant.fragments.photos_fragment.BusinessPhotosViewModel
import com.example.yonetimerchant.fragments.photos_fragment.GridPhotosViewModel
import com.example.yonetimerchant.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAlbumDialogFragment :
    BaseDialogFragment<BusinessPhotosViewModel, FragmentCreateAlbumDialogBinding>() {

    val photosAndAlbumViewModel: BusinessPhotosViewModel by viewModels({ requireParentFragment() })

    override fun getLayout(): Int = R.layout.fragment_create_album_dialog

    override fun bindingToView() {
var name = photosAndAlbumViewModel.selectedTabName
    }

}