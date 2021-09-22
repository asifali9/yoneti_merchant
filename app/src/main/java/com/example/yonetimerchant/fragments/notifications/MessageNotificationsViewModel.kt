package com.example.yonetimerchant.fragments.notifications

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.Messages
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class MessageNotificationsViewModel @ViewModelInject constructor(
    var repository: Repository,
    val preferences: MyPreferences,
    val gson:Gson
):ViewModel(){
    var limit = 10
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId

    var messagesList = MutableLiveData<ArrayList<Messages>>()
    var message = MutableLiveData<String>()
    fun getMessagesNotificationList(offset:Int){
        viewModelScope.launch(Dispatchers.IO) {
            var messagesNotificationResponseList = repository.getMessagesNotificationList(userId, offset, limit, sessionId)
            withContext(Dispatchers.Main)
            {
                if (messagesNotificationResponseList.status)
                    messagesList.value = messagesNotificationResponseList.result.notificationsMessages
                else
                    message.value = messagesNotificationResponseList.message
            }
        }
    }
}