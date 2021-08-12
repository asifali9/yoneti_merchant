package com.example.yonetimerchant.dialog_fragment

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.dialog_fragment.reset_password.ChangePasswordViewModel
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentChangePasswordDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordDialogFragment :BaseDialogFragment<ChangePasswordViewModel, FragmentChangePasswordDialogBinding>(){
    override fun getLayout(): Int = R.layout.fragment_change_password_dialog
    val changePasswordVM:ChangePasswordViewModel by viewModels({requireParentFragment()})
    override fun bindingToView() {
        dataBinding.changePasswordViewModel = vieModel

        changePasswordVM!!.otpRequstFocus.observe(this, Observer { canShowOtp->
            if (canShowOtp)
                dataBinding.otpGroup.visibility = View.VISIBLE
        })

        changePasswordVM!!.changePasswordError.observe(this, Observer { passwordErrorMessage ->
            Toast.makeText(requireContext(), passwordErrorMessage, Toast.LENGTH_SHORT).show()
        })

        changePasswordVM!!.passwordUpdatedMessage.observe(this, Observer { passwordUpdateMessage ->
            Toast.makeText(requireContext(), passwordUpdateMessage, Toast.LENGTH_SHORT).show()
            dismiss()

        })
    }
}