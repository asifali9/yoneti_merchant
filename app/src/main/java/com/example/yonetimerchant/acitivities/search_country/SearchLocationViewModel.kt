package com.example.yoneti.activities.search_country

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.City
import com.example.yoneti.model.Countries
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.launch

class SearchLocationViewModel @ViewModelInject constructor(
    var repository: Repository,
    var preferences: MyPreferences
) : ViewModel() {
    val TAG = SearchLocationViewModel::class.java.simpleName
    var userId = Gson().fromJson(preferences.getUser(), BaseResult::class.java).userId
    var sessionId = Gson().fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var cityList = MutableLiveData<ArrayList<City>>()
    var cityError = MutableLiveData<String>()
    var countryList = MutableLiveData<ArrayList<Countries>>()
    var countryError = MutableLiveData<String>()
    fun searchCountry(keywords: String) {
        try {
            viewModelScope.launch {
                var countryResponse = repository.getCountryList(userId, keywords, sessionId)
                try {
                    if (countryResponse.status)
                        countryList.value = countryResponse.result.countries
                    else
                        countryError.value = countryResponse.message
                } catch (e: Exception) {
                    Log.d(TAG, "searchCityResponse: ${e.message}")
                }
            }
        } catch (exception: Exception) {
            Log.d(TAG, "searchCity: ${exception.message}")
        }
    }

    fun searchCity(cityKeyword: String,countryId:String) {
        try {
            viewModelScope.launch {
                var cityResponse = repository.getCityList(userId,cityKeyword, sessionId,countryId)
                try {
                    if (cityResponse.status)
                        cityList.value = cityResponse.result.cities
                    else
                        cityError.value = cityResponse.message
                } catch (e: Exception) {
                    Log.d(TAG, "searchCityResponse: ${e.message}")
                }
            }
        } catch (exception: Exception) {
            Log.d(TAG, "searchCity: ${exception.message}")
        }
    }
}