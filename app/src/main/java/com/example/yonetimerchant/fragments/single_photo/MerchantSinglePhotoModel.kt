package com.example.yonetimerchant.fragments.single_photo

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MerchantSinglePhotoModel @ViewModelInject constructor(
    private val repository: Repository,
    val preferences: MyPreferences,
    var gson: Gson
) : ViewModel() {
    var likesCount: String = ""
    var commentsCount: String = ""
    val TAG = MerchantSinglePhotoModel::class.java.simpleName
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId

    var isPhotoLikedByMerchant = MutableLiveData<Boolean>()

    fun isPhotoLiked(imageId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var response = repository.isPhotoLiked(sessionId, userId.toString(),imageId)
                withContext(Dispatchers.Main)
                {
                    isPhotoLikedByMerchant.value = response.status
                }
            } catch (timeout: Exception) {
                Log.d(TAG, "isProfileCompleted: ${timeout.message}")
            }

        }
    }


    var imageCommentsCount = MutableLiveData<BaseResult>()
    fun imageAndCommentCount(imageId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var response = repository.imageAndCommentCount(sessionId,imageId)
                withContext(Dispatchers.Main)
                {
                    if (response.status)
                    {
                        likesCount = response.result.singlePhotoLikesCommentsCount+" Likes"
                        commentsCount = response.result.singlePhotoLikesCount+" comments"
                    }
                }
            } catch (timeout: Exception) {
                Log.d(TAG, "isProfileCompleted: ${timeout.message}")
            }

        }
    }

}