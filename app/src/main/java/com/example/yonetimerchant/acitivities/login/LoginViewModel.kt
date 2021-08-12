package com.example.yoneti.activities.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.model.User
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.base.BaseModel
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel @ViewModelInject constructor(
    private val repository: Repository,
    val preferences: MyPreferences
) : ViewModel() {
    private var user = MutableLiveData<User>()
    val TAG = LoginViewModel::class.java.simpleName
    var password: String? = null
    var emailAddress: String? = null
    var loginErrorMessage: String? = null
    var otpMessage = MutableLiveData<String>()

    var isLoginSuccessful = MutableLiveData<Boolean>()
    var hasForgotPassword = MutableLiveData<Boolean>()
    var emptyPasswordField = MutableLiveData<Boolean>()
    var emptyEmailField = MutableLiveData<Boolean>()
    var isOtpFound = MutableLiveData<Boolean>()
    var isLoading = MutableLiveData<Boolean>()

    fun loginUser() {
        if (password?.isNotEmpty() ?: false && emailAddress?.isNotEmpty() ?: false) {
            isLoading.value = true
            viewModelScope.launch(Dispatchers.IO) {

                withContext(Dispatchers.Main)
                {
                    try {
                        /**
                         * testinng json of the respose
                         *
                         */
                        user.value = repository.login(
                            password!!,
                            emailAddress!!,
                            preferences.getDeviceToken()
                        )
                        if ((user.value) != null && (user.value)!!.status) {
                            preferences.setUserId((user.value)!!.result!!.userId)
                            preferences.setLogin(true)
                            preferences.setUser(Gson().toJson((user.value)!!.result))
//                        isLoading.value = false
                            isLoginSuccessful.value = true
                            Log.d(TAG, "loginUser: ${user.value?.status!!}")
                            Log.d(TAG, "loginUser: ${user.value?.message!!}")
                            Log.d(TAG, "checking Json: ${Gson().toJson(user.value)}")
                        } else {
                            loginErrorMessage = (user.value)?.message ?: "Error occured"
                            isLoading.value = false
                        }
                    } catch (e: Exception) {
                        Log.d(TAG, "loginUserException: ${e.message}")
                        loginErrorMessage = e.message
                        isLoading.value = false
                    }
                }
            }
        } else {
            if (password?.isNullOrEmpty() ?: false) {
                emptyPasswordField.value = true
            } else {
                emptyEmailField.value = true
            }
        }
    }
    fun forgotPassword() {
        if (password?.isNotEmpty() ?: false && emailAddress?.isNotEmpty() ?: false) {
            isLoading.value = true
            viewModelScope.launch(Dispatchers.IO) {

                withContext(Dispatchers.Main)
                {
                    try {
                        /**
                         * testinng json of the respose
                         *
                         */
                        user.value = repository.forgotPassword(
                            password!!,
                            emailAddress!!,
                            preferences.getDeviceToken()
                        )
                        if ((user.value) != null && (user.value)!!.status) {
                            preferences.setUserId((user.value)!!.result!!.userId)
                            preferences.setLogin(true)
                            preferences.setUser(Gson().toJson((user.value)!!.result))
//                        isLoading.value = false
                            isLoginSuccessful.value = true
                            Log.d(TAG, "loginUser: ${user.value?.status!!}")
                            Log.d(TAG, "loginUser: ${user.value?.message!!}")
                            Log.d(TAG, "checking Json: ${Gson().toJson(user.value)}")
                        } else {
                            loginErrorMessage = (user.value)?.message ?: "Error occured"
                            isLoading.value = false
                        }
                    } catch (e: Exception) {
                        Log.d(TAG, "loginUserException: ${e.message}")
                        loginErrorMessage = e.message
                        isLoading.value = false
                    }
                }
            }
        } else {
            if (password?.isNullOrEmpty() ?: false) {
                emptyPasswordField.value = true
            } else {
                emptyEmailField.value = true
            }
        }
    }

    fun submitEmailForPassword() {
        Log.d(TAG, "submitEmailForPassword: ")
        hasForgotPassword.value = true
//        settingViewsForPasswordForgot()
    }

    fun requestOTP() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                var otpResponse: BaseModel
                if (emailAddress?.trim()?.isNotEmpty()!!) {
                    otpResponse = repository.sendOtp(preferences.getSessionId()!!, emailAddress!!)
                    withContext(Dispatchers.Main)
                    {
                        if (otpResponse.status)
                            isOtpFound.value = true
                        otpMessage.value = otpResponse.message
                    }
                }

            }
        } catch (e: Exception) {
            Log.d(TAG, "requestOTP: ${e.message}")
        }

    }
}