package com.example.yonetimerchant.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.Services
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.util.ArrayList


class AddServiceViewModel @ViewModelInject constructor(
    var preferences: MyPreferences,
    var repository: Repository,
    var gson: Gson
) : ViewModel() {
    var serviceTitle: String = ""
    var serviceCharges: String = ""
    var serviceEstimatedTime: String = ""
    var addServiceError = MutableLiveData<String>()
    var userId = gson.fromJson(preferences.getUser(),BaseResult::class.java).userId
    var sessionId = gson.fromJson(preferences.getUser(),BaseResult::class.java).sessionId

    fun addService() {
        viewModelScope.launch(Dispatchers.IO) {
            if (serviceTitle.isNotEmpty() && serviceCharges.isNotEmpty() && serviceEstimatedTime.isNotEmpty()) {
                repository.addService(userId.toString(),"categoryId","ServiceId",serviceCharges,serviceEstimatedTime,sessionId)
            } else
                addServiceError.value = "fill all fiFelds"
        }
    }
}