package com.example.yonetimerchant.acitivities.otp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.yoneti.activities.login.LoginActivity
import com.example.yoneti.activities.otp.OTPViewModel
import com.example.yonetimerchant.R
import com.example.yonetimerchant.acitivities.HomeActivity
import com.example.yonetimerchant.databinding.ActivityOtpBinding
import com.example.yonetimerchant.utils.openActivity
import com.example.yonetimerchant.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    val otpViewModel by viewModels<OTPViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_otp)
        binding.otpViewModel = otpViewModel
        otpViewModel.isUserCreated.observe(this, Observer {
            if (it)
            {
                openActivity(LoginActivity::class.java)
                finish()
            }
        })

        otpViewModel.isUserCreatedMsg.observe(this, Observer { otpMessage ->
            showToast(otpMessage)
        })

    }
}