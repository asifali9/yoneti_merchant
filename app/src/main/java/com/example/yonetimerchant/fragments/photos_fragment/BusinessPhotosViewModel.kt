package com.example.yonetimerchant.fragments.photos_fragment

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.Album
import com.example.yoneti.model.GridImage
import com.example.yoneti.model.Profile
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class BusinessPhotosViewModel @ViewModelInject constructor(
    var repository: Repository,
    var preferences: MyPreferences,
) : ViewModel() {
    var tabsList = MutableLiveData<MutableList<Album>>()
    var gridImages = MutableLiveData<MutableList<GridImage>>()
    var responseMessage = MutableLiveData<String>()
    val TAG = BusinessPhotosViewModel::class.java.simpleName
    var selectedTabName: String = ""
    var newAlbumName: String = ""
    var selectedTabAlbumId: String = ""
    var selectedTabNumber: Int = -1
    var sessionId = Gson().fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var userId = Gson().fromJson(preferences.getUser(), BaseResult::class.java).userId
    fun getTabName() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                var tabName = repository.getTabname(userId.toString(),sessionId)
                withContext(Dispatchers.Main){
                    try {
                        if (tabName.status)
                            tabsList.value = tabName.result.album
                    }catch (e:Exception)
                    {
                        Log.d(TAG, "onResponse: ${e.message}")
                    }
                }
            }
        } catch (exception: Exception) {
            Log.d(TAG, "InternetIssue: ${exception.message}")
        }

    }


    fun createAlbum() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                var albumCreatedResponse = repository.createAlbum(userId.toString(),selectedTabName,sessionId)
                withContext(Dispatchers.Main){
                    try {

                    }catch (e:Exception)
                    {
                        Log.d(TAG, "onResponse: ${e.message}")
                    }
                }
            }
        } catch (exception: Exception) {
            Log.d(TAG, "InternetIssue: ${exception.message}")
        }

    }

    var albumNameUpdated = MutableLiveData<Boolean>()
    fun updateAlbum() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                var albumCreatedResponse = repository.updateAlbum(userId.toString(),newAlbumName,sessionId,selectedTabAlbumId)
                withContext(Dispatchers.Main){
                    try {
                        if (albumCreatedResponse.status)
                            albumNameUpdated.value = albumCreatedResponse.status
                        else
                            responseMessage.value = albumCreatedResponse.message
                    }catch (e:Exception)
                    {
                        Log.d(TAG, "onResponse: ${e.message}")
                    }
                }
            }
        } catch (exception: Exception) {
            Log.d(TAG, "InternetIssue: ${exception.message}")
        }

    }

    var profileData = MutableLiveData<Profile>()

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            var response = repository.getUserProfile(sessionId, userId.toString())
            withContext(Dispatchers.Main)
            {
                if (response.status)
                profileData.value = response
            }
        }
    }

}