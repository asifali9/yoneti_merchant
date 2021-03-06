package com.example.yonetimerchant.utils.api

import android.util.Log
import com.example.yoneti.model.Profile
import com.example.yoneti.model.SignupResponse
import com.example.yoneti.model.User
import com.example.yonetimerchant.base.BaseModel
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class RemoteService @Inject constructor(
    var json: JSONObject,
    var apiEndpoints: Endpoints
) {
    val TAG = RemoteService::class.java.simpleName
    suspend fun login(password: String, emailAddress: String, deviceToken: String): User {
        Log.wtf(TAG, "login: $emailAddress +$password")
        try {
            json.put("email", emailAddress)
            json.put("password", password)
            json.put("device_id", deviceToken)
            json.put("device_type", "Android")
            json.put("is_push_notification", "1")
            json.put("app_type", "marchant")

        } catch (e: Exception) {
            Log.wtf(TAG, "loginException: ${e.message}")
        }
        Log.wtf(TAG, "login: ${json.toString()}")
        return apiEndpoints.login(
            json.toString()
        ).await()
    }
    suspend fun forgotPassword(password: String, emailAddress: String, deviceToken: String): User {
        Log.wtf(TAG, "login: $emailAddress +$password")
        try {
            json.put("email", emailAddress)
            json.put("password", password)
            json.put("device_id", deviceToken)
            json.put("device_type", "Android")
            json.put("is_push_notification", "1")
            json.put("app_type", "marchant")

        } catch (e: Exception) {
            Log.wtf(TAG, "loginException: ${e.message}")
        }
        Log.wtf(TAG, "login: ${json.toString()}")
        return apiEndpoints.forgotPassword(
            json.toString()
        ).await()
    }

    suspend fun submitEmail(sessionId: String?, emailAddress: String) {
        return apiEndpoints.submitEmail(emailAddress)
    }

    suspend fun createUser(
        emailAddress: String,
        password: String,
        phone: String,
        appType: String,
        deviceId: String,
        deviceType: String,
        isPushNotification: Int
    ): SignupResponse {
        try {
            json.put("email", emailAddress)
            json.put("password", password)
            json.put("phone_num", phone)
            json.put("device_id", deviceId)
            json.put("device_type", deviceType)
            json.put("is_push_notification", isPushNotification)
            json.put("app_type", appType)
            json.put("is_push_notification", 1)
        } catch (e: Exception) {
            Log.wtf(TAG, "createUserException: ${e.message}")
        }
        Log.wtf(TAG, "createUser: $json")
        return apiEndpoints.signup(json.toString()).await()

    }

    suspend fun verify(otpText: String?, userId: String?): BaseModel {
        try {
            json.put("verificationcode", otpText)
            json.put("id", userId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "verify: ${exception.message}")
        }
        return apiEndpoints.verifyOtp(json.toString()).await()
    }

    suspend fun getUserProfile(sessionId: String, userId: String?): Profile {
        try {
            json.put("user_id", userId ?: "")
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        Log.wtf(TAG, "getUserProfile: $json")
        return apiEndpoints.getUserProfile(sessionId, json.toString()).await()
    }
    suspend fun isPhotoLiked(sessionId: String, userId: String?, merchantId: String, imageId: String?): Profile {
        try {
            json.put("user_id", userId)
            json.put("marchant_id", merchantId)
            json.put("image_id", imageId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        Log.wtf(TAG, "getUserProfile: $json")
        return apiEndpoints.isLikePhoto(sessionId, json.toString()).await()
    }

    suspend fun imageAndCommentsCount(sessionId: String, imageId: String?): Profile {
        try {
            json.put("image_id", imageId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        Log.wtf(TAG, "imageAndCommentsCount: $json")
        return apiEndpoints.imageCommentsCount(sessionId, json.toString()).await()
    }

    suspend fun updateCoverPhoto(
        sessionId: String,
        userId: String?,
        base64CoverPhoto: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("cover_photo", base64CoverPhoto)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.updateCover(sessionId, json.toString()).await()
    }

    suspend fun updateUserPhoto(
        sessionId: String,
        userId: String?,
        base64AvatarPhoto: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("avatar", base64AvatarPhoto)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.updateAvatar(sessionId, json.toString()).await()
    }


    suspend fun updateAddress(
        userId: String,
        verificationCode: String,
        country: String,
        city: String,
        street: String,
        zipCode: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("verificationcode", verificationCode)
            json.put("country", country)
            json.put("city", city)
            json.put("street", street)
            json.put("zip_code", zipCode)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.updateAddress(json.toString()).await()
    }

    suspend fun updatePhoneNumber(
        sessionId: String,
        userId: String,
        verificationCode: String,
        newPhoneNumber: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("verificationcode", verificationCode)
            json.put("newPhoneNumber", newPhoneNumber)

        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.updatePhone(sessionId, json.toString()).await()
    }

    suspend fun getOtp(sessionId: String, userId: String): BaseModel {
        try {
            json.put("user_id", userId)
            json.put("Sessionid", sessionId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.getOtp(sessionId, json.toString()).await()
    }

    suspend fun getHomeCategories(sessionId: String, userId: String): Profile {
        try {
            json.put("user_id", userId)
            json.put("Sessionid", sessionId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.getHomeCategories(sessionId, json.toString()).await()
    }

    suspend fun getCategoryDetails(sessionId: String, userId: String, categoryId: String): Profile {
        try {
            json.put("user_id", userId)
            json.put("category_id", categoryId)
//            json.put("Sessionid", sessionId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.getCategoryDetail(sessionId, json.toString()).await()
    }

    suspend fun bookmarkService(serviceId: String, sessionId: String, userId: String): BaseModel {
        try {
            json.put("user_id", userId)
            json.put("business_id", serviceId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.bookmarkService(sessionId, json.toString()).await()
    }

    suspend fun getBookmarks(userId: String, sessionId: String): Profile {
        try {
            json.put("user_id", userId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.getBookmarks(sessionId, json.toString()).await()
    }

    suspend fun deleteBookmark(bookMarkId: String, userId: String, sessionId: String): Profile {
        try {
            json.put("user_id", userId)
            json.put("id", bookMarkId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        return apiEndpoints.deleteBookmark(sessionId, json.toString()).await()
    }

    suspend fun getDiscovery(
        isPermissionProvided: Boolean,
        userId: Int,
        sessionId: String,
        lat: String,
        long: String
    ): Profile {
        try {
            json.put("user_id", userId.toString())
            json.put("lat", lat)
            json.put("long", long)
            json.put("is_permission", isPermissionProvided)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        Log.wtf(TAG, "getDiscovery: ${json.toString()}")
        return apiEndpoints.discovery(sessionId, json.toString()).await()
    }

    suspend fun getReviews(userId: String, sessionId: String): Profile {
        try {
            json.put("user_id", userId.toString())
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        Log.wtf(TAG, "getDiscovery: ${json.toString()}")
        return apiEndpoints.getReviews(sessionId, json.toString()).await()
    }

    suspend fun getTopYonetiPhotos(userId: String, sessionId: String): Profile {
        try {
            json.put("user_id", userId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getUserProfile: ${exception.message}")
        }
        Log.wtf(TAG, "getDiscovery: ${json.toString()}")
        return apiEndpoints.getTopYonetiPhotos(sessionId, json.toString()).await()
    }

    suspend fun getMerchantDetail(sessionId: String, userId: String): Profile {
        try {
            json.put("user_id", userId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getMerchantDetail: ${exception.message}")
        }
        Log.wtf(TAG, "getMerchantDetail: $json\\\\$sessionId")
        return apiEndpoints.getMerchantDetails(sessionId, json.toString()).await()
    }

    suspend fun getAllReviews(
        sessionId: String,
        userId: String,
        merchantId: String,
        apiType: String,
        pageNumber: Int,
        dataSize: Int
    ): Profile {
        try {
//            {
//                "user_id": "25",
//                "marchant_id": 117,
//                "category_id": 1,
//                "type": "review",
//                "offset": 1,
//                "limit": 10
//            }
            json.put("user_id", userId)
            json.put("marchant_id", merchantId)
            json.put("type", apiType)
            json.put("offset", pageNumber)
            json.put("limit", dataSize)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getAllReviews: ${exception.message}")
        }
        return apiEndpoints.getAllReviews(sessionId, json.toString()).await()
    }

    suspend fun bookMerchant(sessionId: String, merchantId: String): Profile {
        try {
            json.put("user_id", merchantId)
        } catch (e: Exception) {
            Log.wtf(TAG, "bookMerchant: ${e.message}")
        }
        return apiEndpoints.bookMerchant(sessionId, json.toString()).await()
    }

    suspend fun getTabName(merchantId: String, sessionId: String): Profile {
        try {
            json.put("user_id", merchantId)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getTabName: ${exception.message}")
        }
        return apiEndpoints.getTabName(json.toString(), sessionId).await()
    }

    suspend fun getAllYourUploadedImages(
        userId: String,
        sessionId: String,
        pageNumber: Int,
        pageSize: Int
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("offset", pageNumber)
            json.put("limit", pageSize)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getTabName: ${exception.message}")
        }
        Log.wtf(TAG, "AllImages: ${json.toString()}")
        return apiEndpoints.getAllYourUploadedImages(sessionId,json.toString()).await()
    }

    suspend fun getYourUploadedImagesByAlbum(
        albumId: String,
        sessionId: String,
        pageNumber: Int,
        pageSize: Int
    ): Profile {
        try {
            json.put("album_id", albumId)
            json.put("offset", pageNumber)
            json.put("limit", pageSize)
        } catch (exception: Exception) {
            Log.wtf(TAG, "getTabName: ${exception.message}")
        }
        Log.wtf(TAG, "getYourUploadedImagesByAlbum: $json")
        return apiEndpoints.getYourUploadedImagesByAlbum(sessionId, json.toString()).await()
    }

    suspend fun countryList(userId: Int, countryKeyword: String, sessionId: String): Profile {
        try {
            json.put("user_id", userId.toString())
            json.put("name", countryKeyword)
        } catch (e: Exception) {
            Log.wtf(TAG, "cityList: ${e.message}")
        }
        return apiEndpoints.searchCountry(json.toString(), sessionId).await()
    }


    suspend fun cityList(
        userId: Int,
        countryId: String,
        cityKeyword: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId.toString())
            json.put("country_id", countryId)
            json.put("city_name", cityKeyword)
        } catch (e: Exception) {
            Log.wtf(TAG, "cityList: ${e.message}")
        }
        return apiEndpoints.searchCity(json.toString(), sessionId).await()
    }

//    suspend fun getPhotosByAlbum(albumId: String, sessionId: String): Profile {
//        try {
//            json.put("album_id",albumId)
//            json.put("offset",0)
//            json.put("limit",10)
//        }catch (e:java.lang.Exception)
//        {
//            Log.wtf(TAG, "getPhotosByAlbum: ${e.message}")
//        }
//        Log.wtf(TAG, "getPhotosByAlbum: ${json.toString()}")
//        return apiEndpoints.getMerchantPhotos(sessionId,json.toString()).await()
//    }

    suspend fun swapBooking(userId: String, merchantId: String, sessionId: String): Profile {
        try {
            json.put("user_id", userId)
            json.put("swap_user_id", merchantId)
        } catch (e: java.lang.Exception) {
            Log.wtf(TAG, "getPhotosByAlbum: ${e.message}")
        }
        Log.wtf(TAG, "getPhotosByAlbum: ${json.toString()}")
        return apiEndpoints.swapBooking(sessionId, json.toString()).await()
    }

    suspend fun orderMerchant(
        userId: String,
        merchantId: String,
        selectedDate: String,
        timeSlot: String,
        agentId: String,
        serviceList: JSONArray,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("marchant_id", merchantId)
            json.put("order_date", selectedDate)
            json.put("order_time", timeSlot)
            json.put("agent_id", agentId)
            json.put("services", serviceList)
        } catch (e: Exception) {
            Log.wtf(TAG, "orderMerchant: ${e.message}")
        }
        Log.wtf(TAG, "orderMerchant: $json")
        return apiEndpoints.orderMerchant(sessionId, json.toString()).await()
    }

    suspend fun currentBooking(
        userId: Int,
        offset: Int,
        limit: Int,
        bookingStatus: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("offset", offset)
            json.put("limit", limit)
            json.put("order_status", bookingStatus)
        } catch (e: Exception) {
            Log.wtf(TAG, "orderMerchant: ${e.message}")
        }
        Log.wtf(TAG, "orderMerchant: $json")
        return apiEndpoints.currentBooking(sessionId, json.toString()).await()
    }

    suspend fun pastBooking(
        userId: Int,
        offset: Int,
        limit: Int,
        bookingStatus: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("offset", offset)
            json.put("limit", limit)
            json.put("order_status", bookingStatus)
        } catch (e: Exception) {
            Log.wtf(TAG, "orderMerchant: ${e.message}")
        }
        Log.wtf(TAG, "orderMerchant: $json")
        return apiEndpoints.pastBooking(sessionId, json.toString()).await()
    }

    suspend fun getAlertList(
        userId: Int,
        offset: Int,
        limit: Int,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId.toString())
            json.put("offset", offset)
            json.put("limit", limit)
        } catch (e: Exception) {
            Log.wtf(TAG, "orderMerchant: ${e.message}")
        }
        Log.wtf(TAG, "orderMerchant: $json")
        return apiEndpoints.getNotificationsList(sessionId, json.toString()).await()
    }

    suspend fun getNotificationsMessages(
        userId: Int,
        offset: Int,
        limit: Int,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId.toString())
            json.put("offset", offset)
            json.put("limit", limit)
        } catch (e: Exception) {
            Log.wtf(TAG, "notificationMessaages: ${e.message}")
        }
        Log.wtf(TAG, "notificationMessaages: $json")
        return apiEndpoints.getNotificationsMessages(sessionId, json.toString()).await()
    }

    suspend fun acceptOrRejectSwapOrder(
        notificationId: String,
        swapStatus: Boolean,
        sessionId: String
    ): Profile {
        try {
            json.put("notification_id", notificationId)
            json.put("status", swapStatus)
        } catch (e: Exception) {
            Log.wtf(TAG, "acceptOrRejectSwapOrder: ${e.message}")
        }
        Log.wtf(TAG, "acceptOrRejectSwapOrder: $json")
        return apiEndpoints.acceptOrRejectSwapOrder(sessionId, json.toString()).await()
    }

    suspend fun changePassword(
        userId: String,
        oldPassword: String,
        newPassword: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("oldPassword", oldPassword)
            json.put("newPassword", newPassword)
        } catch (e: Exception) {
            Log.wtf(TAG, "changePassword: ${e.message}")
        }
        Log.wtf(TAG, "changePassword: $json")
        return apiEndpoints.acceptOrRejectSwapOrder(sessionId, json.toString()).await()
    }


    /**
     * Service Methods
     */
    suspend fun getCategories(
        userId: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
        } catch (e: Exception) {
            Log.wtf(TAG, "changePassword: ${e.message}")
        }
        return apiEndpoints.getMerchantCategories(sessionId, json.toString()).await()
    }

    suspend fun addServices(
        userId: String,
        categoryId: String,
        serviceId: String,
        serviceCharged: String,
        estimatedTime: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("category_id", categoryId)
            json.put("service_id", serviceId)
            json.put("service_charged", serviceCharged)
            json.put("estimated_time", estimatedTime)
        } catch (e: Exception) {
            Log.wtf(TAG, "changePassword: ${e.message}")
        }
        return apiEndpoints.addServices(sessionId, json.toString()).await()
    }

    suspend fun addAgent(
        userId: String,
        categoryId: String,
        serviceId: String,
        agentName: String,
        agentFee: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("category_id", categoryId)
            json.put("service_id", serviceId)
            json.put("agent_name", agentName)
            json.put("agent_fees", agentFee)
        } catch (e: Exception) {
            Log.wtf(TAG, "changePassword: ${e.message}")
        }
        return apiEndpoints.addAgent(sessionId, json.toString()).await()
    }

    /**
     * services added by merchant
     */
    suspend fun getServicesList(
        userId: String,
        categoryId: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("category_id", categoryId)
        } catch (e: Exception) {
            Log.wtf(TAG, "getServicesList: ${e.message}")
        }
        return apiEndpoints.getServices(sessionId, json.toString()).await()
    }

    /**
     * Active Orders
     */
    suspend fun activeOrders(
        merchantId: String,
        offset: Int,
        limit: Int,
        sessionId: String
    ): Profile {
        try {
            json.put("marchant_id", merchantId)
            json.put("offset", offset)
            json.put("limit", limit)
        } catch (e: Exception) {
            Log.wtf(TAG, "activeOrders: ${e.message}")
        }
        Log.wtf(TAG, "activeOrders:$sessionId   ${json.toString()}")
        return apiEndpoints.activeOrders(sessionId, json.toString()).await()
    }

    /**
     * Completed Orders
     */
    suspend fun completeOrders(
        merchantId: String,
        offset: Int,
        limit: Int,
        sessionId: String
    ): Profile {
        try {
            json.put("marchant_id", merchantId)
            json.put("offset", offset)
            json.put("limit", limit)
        } catch (e: Exception) {
            Log.wtf(TAG, "getServicesList: ${e.message}")
        }
        Log.wtf(TAG, "completeOrders: $json")
        return apiEndpoints.completeOrders(sessionId, json.toString()).await()
    }


    suspend fun getServicesAgainstCategory(
        categoryId: String,
        userId: Int,
        sessionId: String
    ): Profile {
        try {
            json.put("category_id", categoryId)
            json.put("user_id", userId)
        } catch (e: Exception) {
            Log.wtf(TAG, "getServicesList: ${e.message}")
        }
        return apiEndpoints.getServicesAgainstCategory(sessionId, json.toString()).await()
    }

    /**
     * update Service
     */
    suspend fun updateService(
        userId: String,
        serviceId: String,
        serviceCharges: String,
        estimatedTime: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("service_id", serviceId)
            json.put("service_charged", serviceCharges)
            json.put("estimated_time", estimatedTime)
        } catch (e: Exception) {
            Log.wtf(TAG, "changePassword: ${e.message}")
        }
        return apiEndpoints.updateService(sessionId, json.toString()).await()
    }

    suspend fun deleteService(
        userId: String,
        serviceId: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("service_id", serviceId)
        } catch (e: Exception) {
            Log.wtf(TAG, "changePassword: ${e.message}")
        }
        return apiEndpoints.deleteService(sessionId, json.toString()).await()
    }

    suspend fun uploadImage(
        userId: String,
        albumId: String,
        base64AlbumImage: String,
        sessionId: String,
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("album_id", albumId)
            json.put("album_image", base64AlbumImage)
        } catch (e: Exception) {
            Log.wtf(TAG, "changePassword: ${e.message}")
        }
        return apiEndpoints.uploadImage(sessionId, json.toString()).await()
    }

    suspend fun createAlbum(
        userId: String,
        albumName: String,
        sessionId: String,
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("name", albumName)
        } catch (e: Exception) {
            Log.wtf(TAG, "changePassword: ${e.message}")
        }
        return apiEndpoints.createAlbum(sessionId, json.toString()).await()
    }

    suspend fun updateAlbum(
        userId: String,
        albumName: String,
        albumId: String,
        sessionId: String,
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("name", albumName)
            json.put("album_id", albumId)
        } catch (e: Exception) {
            Log.wtf(TAG, "changePassword: ${e.message}")
        }
        Log.wtf(TAG, "updateAlbum: ${json.toString()}")
        return apiEndpoints.updateAlbum(sessionId, json.toString()).await()
    }

    suspend fun cancelOrder(orderId: String, sessionId: String): Profile {
        try {
            json.put("order_id", orderId)
        } catch (e: Exception) {
            Log.wtf(TAG, "cancelOrder: ${e.message}")
        }
        return apiEndpoints.cancelOrders(sessionId, json.toString()).await()
    }

    suspend fun rescheduleOrder(orderId: String, sessionId: String, newOrderTime: String): Profile {
        try {
            json.put("order_id", orderId)
            json.put("start_time", newOrderTime)
        } catch (e: Exception) {
            Log.wtf(TAG, "cancelOrder: ${e.message}")
        }
        return apiEndpoints.rescheduleOrders(sessionId, json.toString()).await()
    }

    suspend fun orderDetails(
        orderId: String,
        pageNumber: Int,
        pageSize: Int,
        sessionId: String
    ): Profile {
        try {
            json.put("order_id", orderId)
            json.put("offset", pageNumber)
            json.put("limit", pageSize)
        } catch (e: Exception) {
            Log.wtf(TAG, "cancelOrder: ${e.message}")
        }
        Log.d(TAG, "orderDetails: ${json.toString()}")
        return apiEndpoints.orderDetails(sessionId, json.toString()).await()
    }


    suspend fun getDashboard(
        merchantId: String,
        pageNumber: Int,
        pageSize: Int,
        sessionId: String
    ): Profile {
        try {
            json.put("marchant_id", merchantId)
            json.put("offset", pageNumber)
            json.put("limit", pageSize)
        } catch (e: Exception) {
            Log.wtf(TAG, "cancelOrder: ${e.message}")
        }
        Log.wtf(TAG, "${sessionId}+getDashboard: $json")
        return apiEndpoints.getDashboard(sessionId, json.toString()).await()
    }

    suspend fun startOrder(orderId: String, sessionId: String): Profile {
        try {
            json.put("order_id", orderId)
        } catch (e: Exception) {
            Log.wtf(TAG, "startOrder: ${e.message}")
        }
        Log.wtf(TAG, "startOrder: $json")
        return apiEndpoints.startOrder(sessionId, json.toString()).await()
    }
    suspend fun completeOrder(orderId: String, sessionId: String): Profile {
        try {
            json.put("order_id", orderId)
        } catch (e: Exception) {
            Log.wtf(TAG, "completeOrder: ${e.message}")
        }
        Log.wtf(TAG, "completeOrders: $json")
        return apiEndpoints.completeOrder(sessionId, json.toString()).await()
    }

    /**
     * getting discovery data
     */
    suspend fun updateServiceLocation(
        userId: String,
        sessionId: String,
        country: String,
        city: String,
        completeAddress: String,
        state: String,
        zipCode: String,
        startTime: String,
        endTime: String,
        businessRange: String,
        businessRadius: String,
        isCurrentLocation: String,
        lat: String,
        lng: String,
    ): Profile {
        try {
            json.put("user_id", userId)
            json.put("country_name", country)
            json.put("city_name", city)
            json.put("address", completeAddress)
            json.put("zip_code", zipCode)
            json.put("zip_code", state)
            json.put("business_start_time", startTime)
            json.put("business_close_time", endTime)
            json.put("business_range", businessRange)
            json.put("business_radius", businessRadius)
            json.put("is_curr_location", isCurrentLocation)
            json.put("current_location_lat", lat)
            json.put("current_location_long", lng)
        } catch (e: Exception) {
            Log.wtf(TAG, "startOrder: ${e.message}")
        }
        Log.wtf(TAG, "updateServiceLocation: ${json.toString()}")
        return apiEndpoints.updateBusinessLocation(sessionId, json.toString()).await()
    }

    /**
     * updating Discovery Data
     */
    suspend fun getServiceLocation(userId: String, sessionId: String): Profile {
        try {
            json.put("user_id", userId)
        } catch (e: Exception) {
            Log.wtf(TAG, "startOrder: ${e.message}")
        }
        return apiEndpoints.getBusinessLocation(sessionId, json.toString()).await()
    }


    suspend fun updateProfile(
        fullName: String,
        website: String,
        userId: Int,
        bio: String,
        dob: String,
        gender: String,
        sessionId: String
    ): Profile {
        try {
            json.put("user_id",userId)
            json.put("fullname",fullName)
            json.put("website",website)
            json.put("user_bio",bio)
            json.put("dob",dob)
            json.put("gender",gender)

        }catch (e:Exception)
        {
            Log.wtf(TAG, "updateProfile: ${e.message}")
        }
        return apiEndpoints.addBasicProfileInfo(sessionId,json.toString()).await()
    }

    suspend fun getNextJobTimer(merchantId: String, sessionId:String): Profile {
        try {
            json.put("marchant_id",merchantId)
        }catch (e:Exception)
        {
            Log.wtf(TAG, "getNextJobTimer: ")
        }
        return apiEndpoints.getNextJobTimer(sessionId,json.toString()).await()
    }

    suspend fun getOrdersStatus(pageNumber: Int,dataSize:Int,merchantId: String, sessionId:String, orderStatus:String): Profile {
        try {
            json.put("marchant_id",merchantId)
            json.put("order_status",orderStatus)
            json.put("offset", pageNumber)
            json.put("limit", dataSize)
        }catch (e:Exception)
        {
            Log.wtf(TAG, "getorderStatus: $json")
        }
        Log.wtf(TAG, "getorderStatus: $json")
        return apiEndpoints.getOrdersStatus(sessionId,json.toString()).await()
    }
}