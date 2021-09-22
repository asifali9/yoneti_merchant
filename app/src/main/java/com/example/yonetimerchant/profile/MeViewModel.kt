package com.example.yonetimerchant.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yonetimerchant.utils.MyPreferences

class MeViewModel @ViewModelInject constructor(
    var preferences: MyPreferences
):ViewModel() {
    var isLogout = MutableLiveData<Boolean>()
    fun onSignoutclick()
    {
        preferences.setLogin(false)
        preferences.setUserId(0)
        isLogout.value = true
    }
}