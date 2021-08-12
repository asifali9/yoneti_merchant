package com.example.yonetimerchant.dialog_fragment

import com.example.yoneti.base.BaseDialogFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentChangeEmailDialogBinding

class ChangeEmailDialogFragment :BaseDialogFragment<Nothing, FragmentChangeEmailDialogBinding>(){
    override fun getLayout(): Int = R.layout.fragment_change_email_dialog

    override fun bindingToView() {
        dataBinding.etEmail.setText( arguments?.getString("oldEmail"))
    }
}