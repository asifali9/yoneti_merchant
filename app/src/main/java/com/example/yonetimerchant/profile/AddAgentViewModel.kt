package com.example.yonetimerchant.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.model.User
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.*


class AddAgentViewModel @ViewModelInject constructor(
    var preferences: MyPreferences,
    var repository: Repository,
    var gson: Gson
) : ViewModel() {
    var agentName: String = ""
    var agentFee: String = ""
    var agentServices: String = ""
    var addServiceError = MutableLiveData<String>()
    var user = gson.fromJson(preferences.getUser(),User::class.java)

    fun addAgent() {
        viewModelScope.launch(Dispatchers.IO) {
            if (agentName.isNotEmpty() && agentFee.isNotEmpty() && agentServices.isNotEmpty()) {
                repository.addAgent("userID","categoryId","ServiceId",agentFee,agentServices,"sessionId")
            } else
                addServiceError.value = "fill all fiFelds"
        }
    }
}