package com.example.yonetimerchant.dialog_fragment.extend_order_time

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.base.SharedBaseTestDialogFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.DialogFragmentExtendOrderTimeBinding
import com.example.yonetimerchant.databinding.DialogFragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExtendOrderTimeDialogFragment : BaseDialogFragment<ExtendOrderTimeViewModel,DialogFragmentExtendOrderTimeBinding>(){
    override fun getLayout(): Int  = R.layout.dialog_fragment_extend_order_time

    @RequiresApi(Build.VERSION_CODES.M)
    override fun bindingToView() {
        var newTime = dataBinding.timepicker.hour.toString()+":"+dataBinding.timepicker.minute
        vieModel!!.extendOrderTime(newTime)
    }

}