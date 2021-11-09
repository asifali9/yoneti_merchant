package com.example.yonetimerchant.dialog_fragment.extend_order_time

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExtendOrderTimeViewModel @ViewModelInject constructor(
    var preferences: MyPreferences,
    var repository: Repository,
    var gson: Gson
) : ViewModel() {
    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId


    fun extendOrderTime(newOrderTime:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var extendOrderResponse = repository.rescheduleOrder(userId.toString(),sessionId,newOrderTime)
            withContext(Dispatchers.Main)
            {

            }
            }catch(exception:Exception)
            {

            }
        }
    }
}