package com.example.yonetimerchant.fragments.orders

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.ActiveOrder
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class PendingOrdersViewModel @ViewModelInject constructor(
    var gson: Gson,
    var preferences: MyPreferences,
    var repository: Repository):ViewModel() {

    var userId = gson.fromJson(preferences.getUser(),BaseResult::class.java).userId
    var sessionId = gson.fromJson(preferences.getUser(),BaseResult::class.java).sessionId
    var pendingOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()

    public fun pendingOrders(pageNumber: Int, pageSize: Int) {
        viewModelScope.launch (Dispatchers.IO){
            var response = repository.activeOrders(userId.toString(),pageNumber,pageSize,sessionId)
            withContext(Dispatchers.Main)
            {
                if (response.status)
                {
                    pendingOrdersList.value = response.result.activeOrdersList
                }
            }
        }

    }

}