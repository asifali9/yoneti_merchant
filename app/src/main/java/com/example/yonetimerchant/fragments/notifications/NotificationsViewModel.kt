package com.example.yonetimerchant.fragments.notifications

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.Notifications
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class NotificationsViewModel @ViewModelInject constructor(
    val repository: Repository,
    val preferences: MyPreferences,
    val gson: Gson
) : ViewModel() {

    var limit = 10
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId

    var message = MutableLiveData<String>()
    var alerts = MutableLiveData<ArrayList<Notifications>>()
    fun getAlertsList(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            var alertListResponse = repository.getAlertList(userId, offset, limit, sessionId)
            withContext(Dispatchers.Main)
            {
                if (alertListResponse.status)
                    alerts.value = alertListResponse.result.notificationsList
                else
                    message.value = alertListResponse.message
            }
        }
    }

    fun acceptOrReject(swapStatus: Boolean, notificationId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var swapResponse = repository.acceptOrRejectSwap(swapStatus,notificationId,sessionId)
            withContext(Dispatchers.Main)
            {
                if (swapResponse.status)
                    message.value = swapResponse.message
                else
                    message.value = swapResponse.message
            }
        }
    }
}