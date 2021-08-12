package com.example.yonetimerchant.fragments.orders

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.ActiveOrder
import com.example.yoneti.model.User
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.util.ArrayList

class ListOfOrdersViewModel @ViewModelInject constructor(
    var gson: Gson,
    var preferences: MyPreferences,
    var repository: Repository
) : ViewModel() {

    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var activeOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()
    var completeOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()
    var listOfOrderErrors = MutableLiveData<String>()

    public fun activeOrders(offset: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repository.activeOrders(userId.toString(), offset, limit, sessionId)
            withContext(Dispatchers.Main)
            {
                if (result.status)
                    activeOrdersList.value = result.result.activeOrdersList
                else
                    listOfOrderErrors.value = result.message
            }
        }
    }

    public fun completedOrders(offset: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            var completedOrders =
                repository.completeOrders(userId.toString(), offset, limit, sessionId)
            withContext(Dispatchers.Main)
            {
                if (completedOrders.status)
                    completeOrdersList.value = completedOrders.result.completeOrdersList
                else
                    listOfOrderErrors.value = completedOrders.message
            }
        }

    }
}