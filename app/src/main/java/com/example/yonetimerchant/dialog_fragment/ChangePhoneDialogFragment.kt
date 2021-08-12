package com.example.yonetimerchant.dialog_fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yoneti.base.SharedBaseDialogFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.acitivities.HomeActivity
import com.example.yonetimerchant.databinding.FragmentChangePhoneDialogBinding
import com.example.yonetimerchant.profile.ProfileViewModel
import com.example.yonetimerchant.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePhoneDialogFragment : SharedBaseDialogFragment<FragmentChangePhoneDialogBinding>() {
    override fun getLayout(): Int = R.layout.fragment_change_phone_dialog

    val vm: ProfileViewModel by viewModels(ownerProducer = { requireParentFragment() })
    override fun bindingToView() {
        dataBinding.profileDialogViewModel = vm

        /**
         * passing old phone from previous fragment
         *
         */
        vm.oldPhone = arguments?.getString("oldPhone")
        arguments?.remove("oldPhone")
        dataBinding.etOldPhone.setText(vm.oldPhone)
        /**
         * focus to otpView
         */
        vm.otpRequstFocus.observe(this, Observer {
            if (it) {
                (activity as HomeActivity).showToast("Enter OTP")
                dataBinding.otpView.requestFocus()
            }
        })
        vm.updateAndDismiss.observe(this, Observer {
            if (it) {
                dismiss()
            }
        })
        vm.changePhoneError.observe(this, Observer {
            Toast.makeText(
                requireContext(),
                it,
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}