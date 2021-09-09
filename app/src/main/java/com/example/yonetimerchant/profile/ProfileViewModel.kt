package com.example.yonetimerchant.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoneti.base.BaseResult
import com.example.yoneti.model.Countries
import com.example.yoneti.model.Orders
import com.example.yoneti.model.Profile
import com.example.yoneti.repository.Repository
import com.example.yonetimerchant.utils.MyPreferences
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Exception
import java.util.ArrayList
class ProfileViewModel @ViewModelInject constructor(
    var preferences: MyPreferences,
    var repository: Repository,
    var gson: Gson
) : ViewModel() {

    var TAG = ProfileViewModel::class.java.simpleName
    var profileData = MutableLiveData<Profile>()
    var fullName: String? = null
    var userName: String? = null
    var website: String? = null
    var bio: String? = null
    var dob: String? = null
    var gender:String? = null
    var phone: String? = null
    var email: String? = null
    var address: String? = null
    var sessionId = gson.fromJson(preferences.getUser(), BaseResult::class.java).sessionId
    var userId = gson.fromJson(preferences.getUser(), BaseResult::class.java).userId
    /**
     * return profile from this function and update UI
     */

    /**
     * Update Email
     */
    var newPhone: String? = null
    var oldPhone: String? = null
    var otp: String? = null
    var otpRequstFocus = MutableLiveData<Boolean>()
    var updateAndDismiss = MutableLiveData<Boolean>()

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            var response = repository.getUserProfile(sessionId, userId.toString())
            withContext(Dispatchers.Main)
            {
                profileData.value = response
            }
        }
    }

    var isProfileCompleted = MutableLiveData<Boolean>()
    fun isProfileCompleted() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var response = repository.getUserProfile(sessionId, userId.toString())
                withContext(Dispatchers.Main)
                {
                    isProfileCompleted.value = response.status
                }
            } catch (timeout: Exception) {
                Log.d(TAG, "isProfileCompleted: ${timeout.message}")
            }

        }
    }

    var dashBoard = MutableLiveData<Profile>()
    fun getDashBoard(pageNumber: Int, pageSize: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            var dashBoardResponse =
                repository.getDashboard(userId.toString(), pageNumber, pageSize, sessionId)
            withContext(Dispatchers.Main)
            {
                if (dashBoardResponse.status)
                    dashBoard.value = dashBoardResponse
            }
        }
    }

    fun updateCoverPhoto(coverPhotoInputStream: InputStream?) {
        val selectedImage = BitmapFactory.decodeStream(coverPhotoInputStream)
        val base64CoverPhoto = encodeImage(selectedImage)
        viewModelScope.launch(Dispatchers.IO) {
            try {

                repository.updateCoverPhoto(
                    sessionId,
                    preferences.getUserId().toString(),
                    base64CoverPhoto
                )
            } catch (exception: Exception) {
                Log.d(TAG, "updateCoverPhoto: ${exception.message}")
            }
        }
    }

    fun updateUserPhoto(inputStream: InputStream?) {
        val selectedImage = BitmapFactory.decodeStream(inputStream)
        val encodedImage = encodeImage(selectedImage)
        Log.d("TAG", "updateUserPhoto: $encodedImage")
        viewModelScope.launch(Dispatchers.IO) {
            try {

                var user = repository.updateUserPhoto(
                    sessionId,
                    preferences.getUserId().toString(),
                    encodedImage
                )
                Log.d(TAG, "updateUserPhoto: ${user.result.avatar}")
            } catch (exception: Exception) {
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


    /**
     * Change Phone Dialog
     */
    var changePhoneError = MutableLiveData<String>()
    fun sendOtp() {
        if (!oldPhone?.isNullOrEmpty()!! && !newPhone?.isNullOrEmpty()!!) {
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    var otpResponse =
                        repository.sendOtp(sessionId, preferences.getUserId().toString())

                    withContext(Dispatchers.Main)
                    {
                        if (otpResponse.status) {
                            otpRequstFocus.value = true
                        }
                    }
                }
            } catch (exception: Exception) {
                Log.d(TAG, "sendOtp: ${exception.message}")
            }
        } else
            changePhoneError.value = "Enter Phone Numbers"
    }

    fun changePhoneNumber() {
        Log.d("TAG", "changePhoneNumber: ${otp}${newPhone}")
        try {

            viewModelScope.launch(Dispatchers.IO) {
                var user = repository.updatePhone(
                    sessionId,
                    preferences.getUserId().toString(),
                    newPhone,
                    otp
                )
                withContext(Dispatchers.Main)
                {
                    if (user.status) {
                        preferences.setUser(Gson().toJson(user))
                        updateAndDismiss.value = true
                    }
                }
            }
        } catch (exception: Exception) {
            changePhoneError.value = exception.message
            Log.d(TAG, "changePhoneNumber: ${exception.message}")
        }
    }

    /**
     * fetch countries
     */
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

    fun changeAddress() {
//        try {
//            viewModelScope.launch {
//                var countryResponse = repository.getCountryList(userId, keywords, sessionId)
//                try {
//                    if (countryResponse.status)
//                        countryList.value = countryResponse.result.countries
//                    else
//                        countryError.value = countryResponse.message
//                } catch (e: Exception) {
//                    Log.d(TAG, "searchCityResponse: ${e.message}")
//                }
//            }
//        } catch (exception: Exception) {
//            Log.d(TAG, "searchCity: ${exception.message}")
//        }
    }

    var activeBookings = MutableLiveData<ArrayList<Orders>>()
    fun getMyCurrentBooking(offset: Int) {
        try {
            viewModelScope.launch {
                var bookingResponse =
                    repository.getCurrentBooking(userId, offset, 10, "active", sessionId)
                try {
                    if (bookingResponse.status)
                        activeBookings.value = bookingResponse.result.activeBooking
                    else
                        countryError.value = bookingResponse.message
                } catch (e: Exception) {
                    Log.d(TAG, "searchCityResponse: ${e.message}")
                }
            }
        } catch (exception: Exception) {
            Log.d(TAG, "searchCity: ${exception.message}")
        }
    }

    var pastBooking = MutableLiveData<List<Orders>>()
    fun getMyPastBooking(offset: Int) {
        try {
            viewModelScope.launch {
                var countryResponse =
                    repository.getCurrentBooking(userId, offset, 10, "active", sessionId)
                try {
                    if (countryResponse.status)
                        pastBooking.value = countryResponse.result.pastBooking
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


    var profileResponseMessage = MutableLiveData<String>()
    fun updateProfile(){
        try{
            viewModelScope.launch(Dispatchers.IO) {
                var profileResponse = repository.updateProfile(fullName?:"",website?:"",userId,bio?:"",dob?:"",gender?:"",sessionId?:"")
                withContext(Dispatchers.Main)
                {
                    if (profileResponse.status)
                        profileResponseMessage.value = profileResponse.message
                    else
                        profileResponseMessage.value = profileResponse.message
                }
            }
        }catch (e:Exception)
        {
            profileResponseMessage.value = e.message
            Log.d(TAG, "updateProfile: ${e.message}")
        }
    }
}