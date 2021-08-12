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

class OrderDetailsViewModel @ViewModelInject constructor(
    var gson: Gson,
    var preferences: MyPreferences,
    var repository: Repository
):ViewModel() {

    var userId = gson.fromJson(preferences.getUser(),BaseResult::class.java).userId
    var sessionId = gson.fromJson(preferences.getUser(),BaseResult::class.java).sessionId
    var activeOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()
    var completeOrdersList = MutableLiveData<ArrayList<ActiveOrder>>()
    var listOfOrderErrors = MutableLiveData<String>()

    public fun cancelOrder(){
        viewModelScope.launch (Dispatchers.IO){
            var result = repository.cancelOrder(userId.toString(),sessionId)
            withContext(Dispatchers.Main)
            {
                if (result.status)
                    completeOrdersList.value = result.result.activeOrdersList
                else
                    listOfOrderErrors.value = result.message
            }
        }
    }
    public fun reschedudleOrders(){
        viewModelScope.launch (Dispatchers.IO){
//            var completedOrders = repository.rescheduleOrder()
            withContext(Dispatchers.Main)
            {

            }
        }

    }
    var isOrderStarted = MutableLiveData<Boolean>()
    public fun startOrder(orderId: String?) {
        viewModelScope.launch (Dispatchers.IO){
            var completedOrders = repository.startOrder(orderId!!,sessionId)
            withContext(Dispatchers.Main)
            {
             isOrderStarted.value = completedOrders.status
            }
        }

    }

    public fun orderDetails(pageNumber: Int, pageSize: Int) {
        viewModelScope.launch (Dispatchers.IO){
            var orderDetailsResponse = repository.orderDetails(userId.toString(),pageNumber,pageSize,sessionId)
            withContext(Dispatchers.Main)
            {

            }
        }

    }
}