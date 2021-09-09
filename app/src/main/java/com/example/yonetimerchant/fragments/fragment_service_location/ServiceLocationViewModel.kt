package com.example.yonetimerchant.fragments.fragment_service_location

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.Service
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServiceLocationViewModel @ViewModelInject constructor(
    var repository: Repository,
    var preferences: MyPreferences
) : ViewModel() {

    var isCurrentLocation = ""
    var lat = ""
    var lng = ""
    var countryName = ""
    var businessRadius = ""
    var businessRange = ""
    var streetAddress = ""
    var city = ""
    var state = ""
    var zipCode = ""
    var businessOpenTime = "Open"
    var businessCloseTime = "Close"

    var sessionId = Gson().fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var userId = Gson().fromJson(preferences.getUser(), BaseResult::class.java).userId
    var businessLocation = MutableLiveData<Service>()

    fun getServiceLocations() {
        viewModelScope.launch(Dispatchers.IO)
        {
            var businessLocationResponse = repository.getServiceLocation(userId.toString(),sessionId)
            withContext(Dispatchers.Main)
            {
                if (businessLocationResponse.status && businessLocationResponse.result.discoveryData != null && businessLocationResponse.result.discoveryData.size > 0 )
                    businessLocation.value = businessLocationResponse.result.discoveryData.get(0)
            }
        }
    }

    fun updateServiceLocations() {
        viewModelScope.launch(Dispatchers.IO)
        {
            var response = repository.updateServiceLocation(
                userId.toString(),
                sessionId,
                countryName,
                streetAddress,
                city,
                state,
                zipCode,
                businessOpenTime,
                businessCloseTime,
                businessRange,
                businessRadius,
                isCurrentLocation,
                lat,
                lng
            )
            withContext(Dispatchers.Main)
            {
                if (response.status)
                {
                    Log.d("TAG", "updateServiceLocations:${response.message} ")
                }
            }
        }
    }
}