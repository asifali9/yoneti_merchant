package com.example.yonetimerchant.fragments.orders

import android.util.Log
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

class OrderDetailsViewModel @ViewModelInject constructor(
    var gson: Gson,
    var preferences: MyPreferences,
    var repository: Repository
) : ViewModel() {

    var details = MutableLiveData<ArrayList<ActiveOrder>>()
    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var activeOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()
    var completeOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()
    var listOfOrderErrors = MutableLiveData<String>()

    public fun cancelOrder(orderId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repository.cancelOrder(orderId.toString(), sessionId)
            withContext(Dispatchers.Main)
            {
                if (result.status) {
//                    completeOrdersList.value = result.result.activeOrdersList
                    Log.d("TAG", "cancelOrder: successful")
                } else
                    listOfOrderErrors.value = result.message
            }
        }
    }

    public fun reschedudleOrders() {
        viewModelScope.launch(Dispatchers.IO) {
//            var completedOrders = repository.rescheduleOrder()
            withContext(Dispatchers.Main)
            {

            }
        }

    }

    var isOrderStarted = MutableLiveData<Boolean>()
    public fun startOrder(orderId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            var completedOrders = repository.startOrder(orderId!!, sessionId)
            withContext(Dispatchers.Main)
            {
                isOrderStarted.value = completedOrders.status
            }
        }

    }

    public fun orderDetails(pageNumber: Int, pageSize: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            var orderDetailsResponse =
                repository.activeOrders(userId.toString(), pageNumber, pageSize, sessionId)
            withContext(Dispatchers.Main)
            {
                if (orderDetailsResponse.status) {
                    details.value = orderDetailsResponse.result.activeOrdersList
                }
            }
        }

    }
}