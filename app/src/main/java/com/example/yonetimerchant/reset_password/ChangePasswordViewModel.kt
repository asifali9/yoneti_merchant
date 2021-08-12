package com.example.yoneti.dialog_fragment.reset_password

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ChangePasswordViewModel @ViewModelInject constructor(
    val repository: Repository,
    val preferences: MyPreferences,
    val gson: Gson,
) : ViewModel() {
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId
    var oldPassword: String = ""
    var newPassword: String = ""

    val TAG = ChangePasswordViewModel::class.java.simpleName
    var changePasswordError = MutableLiveData<String>()
    var passwordUpdatedMessage = MutableLiveData<String>()
    var otpRequstFocus = MutableLiveData<Boolean>()
    fun sendOtp() {
        if (!oldPassword?.isNullOrEmpty()!! && !newPassword?.isNullOrEmpty()!!) {
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    var otpResponse =
                        repository.sendOtp(sessionId, preferences.getUserId().toString())

                    withContext(Dispatchers.Main)
                    {
                        if (otpResponse.status) {
                            otpRequstFocus.value = true
                        }
                    }
                }
            } catch (exception: Exception) {
                Log.d(TAG, "sendOtp: ${exception.message}")
                changePasswordError.value = exception.message
            }
        } else
            changePasswordError.value = "Enter Phone Numbers"
    }


    fun changePassword()
    {
        viewModelScope.launch(Dispatchers.IO) {
            var changePasswordResponse = repository.changePassword(userId.toString(),sessionId,oldPassword,newPassword)
            withContext(Dispatchers.Main)
            {
                if (changePasswordResponse.status) {
                    passwordUpdatedMessage.value = changePasswordResponse.message
                }else
                    changePasswordError.value = changePasswordResponse.message
            }
        }
    }
}