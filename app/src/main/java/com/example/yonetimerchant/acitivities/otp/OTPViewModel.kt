package com.example.yoneti.activities.otp

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OTPViewModel @ViewModelInject constructor(
    var repository: Repository,
    var preferences: MyPreferences
):ViewModel() {
    var otpText:String? = null
    var isUserCreated = MutableLiveData<Boolean>()
    var isUserCreatedMsg = MutableLiveData<String>()
    fun onOtpClick()
    {
        viewModelScope.launch(Dispatchers.IO) {
            var response = repository.verifyOTP(otpText,preferences.getUserId().toString())
            withContext(Dispatchers.Main)
            {
                if (response.status)
                {
                    preferences.setLogin(true)
                    isUserCreated.value = true
                }
                else
                    isUserCreatedMsg.value = response.message
            }
        }

    }
}