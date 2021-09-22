package com.example.yoneti.repository

import android.util.Log
import com.example.yoneti.model.Profile
import com.example.yoneti.model.User
import com.example.yonetimerchant.utils.api.RemoteService
import org.json.JSONArray
import javax.inject.Inject

class Repository @Inject constructor(var remoteService: RemoteService) {
    suspend fun login(password: String, emailAddress: String, deviceToken: String?): User {
        return remoteService.login(password, emailAddress, deviceToken!!)
    }

    suspend fun forgotPassword(password: String, emailAddress: String, deviceToken: String?): User {
        return remoteService.forgotPassword(password, emailAddress, deviceToken!!)
    }

    suspend fun submitEmailForOTP(sessionId: String?, emailAddress: String) {
        return remoteService.submitEmail(sessionId, emailAddress)
    }

    suspend fun signup(
        email: String,
        password: String,
        phone: String,
        appType: String,
        deviceId: String,
        devicetype: String,
        isPushNotification: Int
    ) = remoteService.createUser(
        email,
        password,
        phone,
        appType,
        deviceId,
        devicetype,
        isPushNotification
    )

    suspend fun verifyOTP(otpText: String?, userId: String?) = remoteService.verify(otpText, userId)

    suspend fun getUserProfile(sessionId: String, userId: String?) =
        remoteService.getUserProfile(sessionId, userId)

    suspend fun isPhotoLiked(sessionId: String, userId: String?, imageId: String?) =
        remoteService.isPhotoLiked(
            sessionId,
            userId,
            userId!!,
            imageId
        )// second userid  is  the merchant id

    suspend fun imageAndCommentCount(sessionId: String, imageId: String?) =
        remoteService.imageAndCommentsCount(sessionId, imageId)

    suspend fun updateCoverPhoto(sessionId: String?, userId: String?, base64CoverPhoto: String?) =
        remoteService.updateCoverPhoto(sessionId!!, userId!!, base64CoverPhoto!!)

    suspend fun updateUserPhoto(sessionId: String?, userId: String?, base64UserPhoto: String?) =
        remoteService.updateUserPhoto(sessionId!!, userId!!, base64UserPhoto!!)

    /**
     * for album
     */
    suspend fun uploadImage(
        sessionId: String?,
        userId: String?,
        base64UserPhoto: String?,
        albumId: String
    ) =
        remoteService.uploadImage(userId!!, albumId, base64UserPhoto!!, sessionId!!)

    /**
     * Request for otp
     */
    suspend fun sendOtp(sessionId: String, userId: String) = remoteService.getOtp(sessionId, userId)
    suspend fun updatePhone(sessionId: String?, userId: String, newPhone: String?, otp: String?) =
        remoteService.updatePhoneNumber(sessionId!!, userId, otp!!, newPhone!!)

    suspend fun getHomeCategories(sessionId: String, userId: Int) =
        remoteService.getHomeCategories(sessionId, userId.toString())

    suspend fun getCategoryDetails(sessionId: String, userId: Int, categoryId: String) =
        remoteService.getCategoryDetails(sessionId, userId.toString(), categoryId)

    suspend fun bookmarkService(serviceId: String?, sessionId: String, userId: Int) =
        remoteService.bookmarkService(serviceId!!, sessionId, userId.toString())

    suspend fun getBookmarks(userId: Int, sessionId: String) =
        remoteService.getBookmarks(userId.toString(), sessionId)

    suspend fun removeBookmark(bookmarkId: String, userId: Int, sessionId: String) =
        remoteService.deleteBookmark(bookmarkId, userId.toString(), sessionId)

    suspend fun getDiscovery(
        userId: Int,
        sessionId: String,
        lat: String,
        long: String,
        isPermissionProvided: Boolean
    ) = remoteService.getDiscovery(isPermissionProvided, userId, sessionId, lat, long)

    suspend fun getReviews(sessionId: String, userId: Int) =
        remoteService.getReviews(userId.toString(), sessionId)

    suspend fun getTopYonetiPhotos(sessionId: String, userId: Int) =
        remoteService.getTopYonetiPhotos(userId.toString(), sessionId)

    suspend fun getMerchantDetail(sessionId: String, merchantId: String): Profile {
        Log.d("repo", "getMerchantDetail: $sessionId+$merchantId")
        return remoteService.getMerchantDetail(sessionId, merchantId)
    }

    suspend fun getAllReviews(
        sessionId: String,
        userId: Int,
        pageNumber: Int,
        dataSize: Int,
        merchantId: String,
        apiType: String
    ) = remoteService.getAllReviews(
        sessionId,
        userId.toString(),
        merchantId,
        apiType,
        pageNumber,
        dataSize
    )

    suspend fun bookMerchant(sessionId: String, merchantId: String) =
        remoteService.bookMerchant(sessionId, merchantId)

    suspend fun getTabname(merchantId: String, sessionId: String) =
        remoteService.getTabName(merchantId, sessionId)

    suspend fun getYourUploadedImagesByAlbum(
        albumId: String,
        sessionId: String,
        pageNumber: Int,
        pageSize: Int
    ) = remoteService.getYourUploadedImagesByAlbum(albumId, sessionId, pageNumber, pageSize)

    suspend fun getYourAllUploadedImages(
        userId: String,
        sessionId: String,
        pageNumber: Int,
        pageSize: Int
    ) = remoteService.getAllYourUploadedImages(userId, sessionId, pageNumber, pageSize)

    suspend fun getCountryList(userId: Int, cityKeyword: String, sessionId: String) =
        remoteService.countryList(userId, cityKeyword, sessionId)

    suspend fun getCityList(
        userId: Int,
        cityKeyword: String,
        sessionId: String,
        countryId: String
    ) = remoteService.cityList(userId, countryId, cityKeyword, sessionId)

//    suspend fun getPhotos(albumId: String, sessionId: String) =
//        remoteService.getPhotosByAlbum(albumId, sessionId)

    suspend fun orderMerchant(
        userId: String,
        merchantId: String,
        selectedDate: String,
        timeSlot: String,
        agentId: String,
        serviceList: JSONArray,
        sessionId: String
    ): Profile {
        return remoteService.orderMerchant(
            userId,
            merchantId,
            selectedDate,
            timeSlot,
            agentId,
            serviceList,
            sessionId
        )
    }

    suspend fun requestSwap(userId: Int, sessionId: String, merchantId: String): Profile {
        return remoteService.swapBooking(userId.toString(), merchantId, sessionId)
    }

    suspend fun getCurrentBooking(
        userId: Int,
        offset: Int,
        limit: Int,
        bookingStatus: String,
        sessionId: String
    ): Profile {
        return remoteService.currentBooking(userId, offset, limit, bookingStatus, sessionId)
    }

    suspend fun getPastBooking(
        userId: Int,
        offset: Int,
        limit: Int,
        bookingStatus: String,
        sessionId: String
    ): Profile {
        return remoteService.pastBooking(userId, offset, limit, bookingStatus, sessionId)
    }

    suspend fun getAlertList(userId: Int, offset: Int, limit: Int, sessionId: String): Profile {
        return remoteService.getAlertList(userId, offset, limit, sessionId)
    }

    suspend fun getMessagesNotificationList(
        userId: Int,
        offset: Int,
        limit: Int,
        sessionId: String
    ): Profile {
        return remoteService.getNotificationsMessages(userId, offset, limit, sessionId)
    }

    suspend fun acceptOrRejectSwap(
        swapStatus: Boolean,
        notificationId: String,
        sessionId: String
    ): Profile {
        return remoteService.acceptOrRejectSwapOrder(notificationId, swapStatus, sessionId)
    }

    suspend fun changePassword(
        userId: String,
        sessionId: String,
        oldPassword: String,
        newPassword: String
    ): Profile {
        return remoteService.changePassword(userId, oldPassword, newPassword, sessionId)
    }

    /**
     * Add agent
     */
    suspend fun addAgent(
        userID: String,
        categoryId: String,
        ServiceId: String,
        serviceCharges: String,
        serviceEstimatedTime: String,
        sessionId: String
    ) = remoteService.addAgent(
        userID,
        categoryId,
        ServiceId,
        serviceCharges,
        serviceEstimatedTime,
        sessionId
    )


    suspend fun addService(
        userID: String,
        categoryId: String,
        ServiceId: String,
        serviceCharges: String,
        serviceEstimatedTime: String,
        sessionId: String
    ) = remoteService.addServices(
        userID,
        categoryId,
        ServiceId,
        serviceCharges,
        serviceEstimatedTime,
        sessionId
    )

    suspend fun getCategoryServices(
        userId: String,
        categoryId: String,
        sessionId: String
    ): Profile {
        return remoteService.getServicesList(userId, categoryId, sessionId)
    }

    suspend fun activeOrders(
        merchantId: String,
        offset: Int,
        limit: Int,
        sessionId: String
    ): Profile {
        return remoteService.activeOrders(merchantId, offset, limit, sessionId)
    }

    suspend fun completeOrders(
        merchantId: String,
        offset: Int,
        limit: Int,
        sessionId: String
    ): Profile {
        return remoteService.completeOrders(merchantId, offset, limit, sessionId)
    }

    suspend fun getServicesAgainstCategory(
        categoryId: String,
        userId: Int,
        sessionId: String
    ): Profile {
        return remoteService.getServicesAgainstCategory(categoryId, userId, sessionId)
    }

    suspend fun getCategories(userId: String, sessionId: String): Profile {
        return remoteService.getCategories(userId, sessionId)
    }

    suspend fun updateService(
        userId: String,
        serviceId: String,
        serviceCharges: String,
        estimatedTime: String,
        sessionId: String
    ): Profile {
        return remoteService.updateService(
            userId,
            serviceId,
            serviceCharges,
            estimatedTime,
            sessionId
        )
    }

    suspend fun deleteService(userId: String, serviceId: String, sessionId: String): Profile {
        return remoteService.deleteService(
            userId,
            serviceId,
            sessionId
        )
    }


    suspend fun createAlbum(
        userId: String,
        albumName: String,
        sessionId: String,
    ): Profile {
        return remoteService.createAlbum(
            userId,
            albumName,
            sessionId
        )
    }

    suspend fun updateAlbum(
        userId: String,
        albumName: String,
        sessionId: String,
        selectedTabAlbumId: String,
    ): Profile {
        return remoteService.updateAlbum(
            userId,
            albumName,
            selectedTabAlbumId,
            sessionId
        )
    }

    suspend fun cancelOrder(orderId: String, sessionId: String): Profile {
        return remoteService.cancelOrder(orderId, sessionId)
    }

    suspend fun rescheduleOrder(userId: String, sessionId: String): Profile {
        return remoteService.rescheduleOrder(userId, sessionId)
    }

    suspend fun orderDetails(
        userId: String, pageNumber: Int,
        pageSize: Int, sessionId: String
    ): Profile {
        return remoteService.orderDetails(userId, pageNumber, pageSize, sessionId)
    }
    suspend fun getDashboard(
        merchantId: String, pageNumber: Int,
        pageSize: Int, sessionId: String
    ): Profile {
        return remoteService.getDashboard(merchantId, pageNumber, pageSize, sessionId)
    }

    suspend fun startOrder(orderId: String, sessionId: String): Profile {
        return remoteService.startOrder(orderId, sessionId)
    }

    suspend fun getServiceLocation(userId:String, sessionId: String) = remoteService.getServiceLocation(userId,sessionId)

    suspend fun updateServiceLocation(
        userId: String,
        sessionId: String,
        countryName: String,
        streetAddress: String,
        city: String,
        state: String,
        zipCode: String,
        businessOpenTime: String,
        businessCloseTime: String,
        businessRange: String,
        businessRadius: String,
        isCurrentLocation: String,
        lat: String,
        lng: String
    ): Profile {
        return remoteService.updateServiceLocation(
            userId,
            sessionId,
            countryName,
            city,
            streetAddress,
            state,
            zipCode,
            businessOpenTime,
            businessCloseTime,
            businessRange,
            businessRadius,
            isCurrentLocation,
            lat,
            lng)
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
        return remoteService.updateProfile(fullName,website,userId,bio,dob,gender,sessionId)
    }

    suspend fun getNextJobTimer(userId: Int, sessionId: String) = remoteService.getNextJobTimer(userId.toString(),sessionId)
}