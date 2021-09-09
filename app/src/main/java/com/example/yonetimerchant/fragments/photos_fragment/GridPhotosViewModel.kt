package com.example.yonetimerchant.fragments.photos_fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.Album
import com.example.yoneti.model.GridImage
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Exception

class GridPhotosViewModel @ViewModelInject constructor(
    var repository: Repository,
    var preferences: MyPreferences
) : ViewModel() {
    var tabsList = MutableLiveData<MutableList<Album>>()
    var gridImages = MutableLiveData<MutableList<GridImage>>()
    val TAG = GridPhotosViewModel::class.java.simpleName
    var sessionId = Gson().fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var userId = Gson().fromJson(preferences.getUser(), BaseResult::class.java).userId

    fun getAllUploadedPhotos(pageNumber: Int, pageSize: Int) {
        try {

            viewModelScope.launch(Dispatchers.IO) {
                var photosList = repository.getYourAllUploadedImages(userId.toString(),sessionId,pageNumber,pageSize)
                withContext(Dispatchers.Main){
                    try {
                        if (photosList.status)
                            gridImages.value = photosList.result.gridImages
                    }catch (e:Exception)
                    {
                        Log.d(TAG, "onResponse: ${e.message}")
                    }
                }
            }

        }catch (exception:Exception)
        {
            Log.d(TAG, "getPhotos: ${exception.message}")
        }
    }

    fun getPhotos(albumId: String,pageNumber: Int, pageSize: Int) {
        try {

            viewModelScope.launch(Dispatchers.IO) {
                var photosList = repository.getYourUploadedImagesByAlbum(albumId,sessionId,pageNumber,pageSize)
                withContext(Dispatchers.Main){
                    try {
                        if (photosList.status)
                            gridImages.value = photosList.result.gridImages
                    }catch (e:Exception)
                    {
                        Log.d(TAG, "onResponse: ${e.message}")
                    }
                }
            }

        }catch (exception:Exception)
        {
            Log.d(TAG, "getPhotos: ${exception.message}")
        }
    }

    fun uploadPhoto(inputStream: InputStream?, albumId: String)
    {
        val selectedImage = BitmapFactory.decodeStream(inputStream)
        val encodedImage = encodeImage(selectedImage)
        Log.d("TAG", "updateUserPhoto: $encodedImage")
        viewModelScope.launch(Dispatchers.IO) {
            try {

                var imageUploadedResponse = repository.uploadImage(sessionId,preferences.getUserId().toString(),encodedImage,albumId)
                if (imageUploadedResponse.status)
                    gridImages.value = imageUploadedResponse.result.gridImages
            }catch (exception:Exception)
            {
                Log.d(TAG, "updateUserPhoto: ${exception.message}")
            }
        }
    }

    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

}