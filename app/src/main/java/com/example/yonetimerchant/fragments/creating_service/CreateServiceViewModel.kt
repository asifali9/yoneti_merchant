package com.example.yonetimerchant.fragments.creating_service

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.Categories
import com.example.yoneti.model.MerchantServices
import com.example.yoneti.model.Services
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class CreateServiceViewModel @ViewModelInject constructor(
    var preferences: MyPreferences,
    var repository: Repository,
    var gson: Gson
) : ViewModel() {
    var newlyCreatedService: MerchantServices? = null
    val TAG = CreateServiceViewModel::class.java.simpleName
    private var servicesError = MutableLiveData<String>()
    var serviceCateogryList = MutableLiveData<ArrayList<Categories>>()
    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId


    var merchantServices = MutableLiveData<ArrayList<MerchantServices>>()
    fun getServicesAgainstId(catId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var merchantServicesResult =
                repository.getServicesAgainstCategory(catId, userId, sessionId)
            withContext(Dispatchers.Main)
            {
                if (merchantServicesResult.status)
                    merchantServices.value = merchantServicesResult.result.merchantServices
            }
        }
    }

    fun getServiceCategories() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                var categoryResult = repository.getCategories(userId.toString(), sessionId)
                withContext(Dispatchers.Main)
                {
                    if (categoryResult.status)
                        serviceCateogryList.value = categoryResult.result.categoryList
                    else
                        servicesError.value = categoryResult.message
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "getServiceCategories: ${e.message}")
        }

    }

    var services = MutableLiveData<ArrayList<Services>>()
    fun getCategoryService(categoryId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var servicesResult =
                repository.getCategoryServices(userId.toString(), categoryId, sessionId)
            withContext(Dispatchers.Main)
            {
                if (servicesResult.status)
                    services.value = servicesResult.result.serviceList
                else
                    servicesError.value = "fill all fiFelds"
            }
        }
    }

    var serviceAddedSuccessful = MutableLiveData<Boolean>()
    fun registeredNewService(
        serviceTitle: String,
        categoryId: String,
        serviceId: String,
        serviceCharges: String,
        serviceEstimatedTime: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (serviceTitle.isNotEmpty() && serviceCharges.isNotEmpty() && serviceEstimatedTime.isNotEmpty()) {
                var addNewServiceResult = repository.addService(
                    userId.toString(),
                    categoryId,
                    serviceId,
                    serviceCharges,
                    serviceEstimatedTime,
                    sessionId
                )

                withContext(Dispatchers.Main) {
                    if (addNewServiceResult.status) {
                        newlyCreatedService = MerchantServices(
                            serviceId,
                            userId.toString(),
                            serviceTitle,
                            serviceCharges,
                            serviceEstimatedTime,
                            categoryId
                        )
                        serviceAddedSuccessful.value = addNewServiceResult.status
                    } else
                        servicesError.value = addNewServiceResult.message
                }
            } else
                withContext(Dispatchers.Main)
                {
                    servicesError.value = "fill all fields"
                }
        }
    }

    /**
     * Add agent
     */
    var agentName: String? = null
    var agentFee: String? = null
    fun addAgent(
        serviceTitle: String,
        categoryId: String,
        serviceId: String,
        agentFee: String,
        agentName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (serviceTitle.isNotEmpty() && agentFee.isNotEmpty() && agentName.isNotEmpty()) {
                var addNewServiceResult = repository.addAgent(
                    userId.toString(),
                    categoryId,
                    serviceId,
                    agentFee,
                    agentName,
                    sessionId
                )

                if (addNewServiceResult.status)
                    serviceAddedSuccessful.value = addNewServiceResult.status
            } else
                servicesError.value = "fill all fields"

        }
    }

    var isServiceUpdated = MutableLiveData<Boolean>()

    fun updateService(serviceId: String, serviceCharges: String, estimatedTime: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var isServiceUpdatedResponse = repository.updateService(userId.toString(),serviceId,serviceCharges,estimatedTime,sessionId)
            withContext(Dispatchers.Main)
            {
                if (isServiceUpdatedResponse.status)
                    isServiceUpdated.value = isServiceUpdatedResponse.status
                else
                    servicesError.value = isServiceUpdatedResponse.message
            }
        }
    }

    var isServiceDeleted = MutableLiveData<Boolean>()
    fun deleteService(serviceId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var isServiceUpdated = repository.deleteService(userId.toString(),serviceId,sessionId)
            withContext(Dispatchers.Main)
            {
                if (isServiceUpdated.status)
                    isServiceDeleted.value = isServiceUpdated.status
                else
                    servicesError.value = isServiceUpdated.message
            }
        }
    }
}