package com.example.yonetimerchant.fragments.tracking

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.ActiveOrder
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.model.QueueOrders
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class OrderTrackingViewModel @ViewModelInject constructor(
    var gson: Gson,
    var preferences: MyPreferences,
    var repository: Repository
) : ViewModel() {

    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var activeOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()
    var completeOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()
    var cancelOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()
    var queueOrdersList = MutableLiveData<ArrayList<QueueOrders>>()
    var listOfOrderErrors = MutableLiveData<String>()

    public fun activeOrders(offset: Int, limit: Int, orderStatus: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repository.getOrdersStatus(userId,sessionId,orderStatus, offset, limit)
            withContext(Dispatchers.Main)
            {
                if (result.status)
                    activeOrdersList.value = result.result.activeOrdersList
                else
                    listOfOrderErrors.value = result.message
            }
        }
    }

    public fun completedOrders(offset: Int, limit: Int, orderStatus: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var completedOrders =
                repository.getOrdersStatus(userId, sessionId, orderStatus,offset, limit)
            withContext(Dispatchers.Main)
            {
                if (completedOrders.status)
                    completeOrdersList.value = completedOrders.result.progressTrackingOrdersList
                else
                    listOfOrderErrors.value = completedOrders.message
            }
        }

    }

    public fun cancelOrders(offset: Int, limit: Int, orderStatus: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var cancelOrders =
                repository.getOrdersStatus(userId,sessionId,orderStatus, offset, limit)
            withContext(Dispatchers.Main)
            {
                if (cancelOrders.status)
                    cancelOrdersList.value = cancelOrders.result.cancelOrdersList
                else
                    listOfOrderErrors.value = cancelOrders.message
            }
        }

    }
    public fun queueOrders(offset: Int, limit: Int, orderStatus: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var queueOrders = repository.getOrdersStatus(userId,sessionId,orderStatus, offset, limit)
            withContext(Dispatchers.Main)
            {
                if (queueOrders.status)
                    queueOrdersList.value = queueOrders.result.pendingOrdersList
                else
                    listOfOrderErrors.value = queueOrders.message
            }
        }

    }
}