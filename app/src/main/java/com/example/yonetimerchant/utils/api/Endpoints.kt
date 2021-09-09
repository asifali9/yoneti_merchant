package com.example.yonetimerchant.utils.api

import com.example.yoneti.model.Profile
import com.example.yoneti.model.SignupResponse
import com.example.yoneti.model.User
import com.example.yonetimerchant.base.BaseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface Endpoints {
    val DEVICE_TYPE: String
        get() = "Android"

    val DEVICE_ID: String
        get() = "test123"

    val APP_TYPE: String
        get() = "marchant"
    val ACCESS_TOKEN: String
        get() = "b8d7996ea3557791aba04cf51a0654b2"


    @Headers("Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/login")
    fun login(
        @Body jsonParam: String
    ): Deferred<User>

    @Headers("Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/forgotPassword")
    fun forgotPassword(
        @Body jsonParam: String
    ): Deferred<User>

    @FormUrlEncoded
    @POST("Marchant_album/getAlbumByUser")
    fun submitEmail(
        @Field("") emailAddress: String
    )

    @Headers("content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/signup")
    fun signup(
        @Body userCredentials: String,
    ): Deferred<SignupResponse>

    @Headers("accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/verification")
    fun verifyOtp(@Body verfiyOTPJson: String): Deferred<BaseModel>

    @Headers("accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/getMarchantProfile")
    fun getUserProfile(
        @Header("Sessionid") sessionId: String,
        @Body jsonUserId: String
    ): Deferred<Profile>

    /**
     * for updating cover photo
     */
    @Headers("accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/updateCoverPhoto")
    fun updateCover(
        @Header("Sessionid") sessionId: String,
        @Body updatePhotoJsonObject: String
    ): Deferred<Profile>

    @Headers("accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/updateAvatar")
    fun updateAvatar(
        @Header("Sessionid") sessionId: String,
        @Body updateAvatarJson: String
    ): Deferred<Profile>

    @Headers("accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/updatePhoneNumber")
    fun updatePhone(
        @Header("Sessionid") sessionId: String,
        @Body updatePhoneJson: String
    ): Deferred<Profile>

    /**
     * get otp for updating phone address
     */
    @Headers("accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/sendOTP")
    fun getOtp(
        @Header("Sessionid") sessionId: String,
        @Body userId: String
    ): Deferred<BaseModel>

    @Headers("accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/getCategories")
    fun getHomeCategories(
        @Header("Sessionid") sessionId: String,
        @Body userId: String
    ): Deferred<Profile>

    @Headers("accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/getMarchantList")
    fun getCategoryDetail(
        @Header("Sessionid") sessionId: String,
        @Body homeDetails: String
    ): Deferred<Profile>

    @Headers("accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/addBookmark")
    fun bookmarkService(
        @Header("Sessionid") sessionId: String,
        @Body bookmarkJson: String
    ): Deferred<BaseModel>

    @Headers("content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/getBookmark")
    fun getBookmarks(
        @Header("Sessionid") sessionId: String,
        @Body jsonBody: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/deleteBookmark")
    fun deleteBookmark(
        @Header("Sessionid") sessionId: String,
        @Body jsonBody: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Discovery/getUserDiscovery")
    fun discovery(
        @Header("Sessionid") sessionId: String,
        @Body toString: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Finders/reviews")
    fun getReviews(
        @Header("Sessionid") sessionId: String,
        @Body toString: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Finders/photos")
    fun getTopYonetiPhotos(
        @Header("Sessionid") sessionId: String,
        @Body toString: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/getMarchantAllDetails")
    fun getMerchantDetails(
        @Header("Sessionid") sessionId: String,
        @Body toString: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/getAllReviews")
    fun getAllReviews(
        @Header("Sessionid") sessionId: String,
        @Body jsonBody: String
    ): Deferred<Profile>


    @Headers("content-Type:application/json", "accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_album/getImagesAgainstUser")
    fun getAllYourUploadedImages(
        @Header("Sessionid") sessionId: String,
        @Body jsonBody: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_album/addImages")
    fun uploadImage(
        @Header("Sessionid") sessionId: String,
        @Body jsonBody: String
    ): Deferred<Profile>


    @Headers("content-Type:application/json", "accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_album/createAlbum")
    fun createAlbum(
        @Header("Sessionid") sessionId: String,
        @Body jsonBody: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_album/updateAlbum")
    fun updateAlbum(
        @Header("Sessionid") sessionId: String,
        @Body jsonBody: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_album/getAlbumByUser")
    fun getTabName(
        @Body jsonBody: String,
        @Header("Sessionid") sessionId: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Discovery/searchCountry")
    fun searchCountry(
        @Body jsonBody: String,
        @Header("Sessionid") sessionId: String
    ): Deferred<Profile>

    @Headers("content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Discovery/searchCity")
    fun searchCity(
        @Body jsonBody: String,
        @Header("Sessionid") sessionId: String
    ): Deferred<Profile>

    /**
     * Book Merchant
     */
    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/reserveSeat")
    fun bookMerchant(
        @Header("Sessionid") sessionId: String,
        @Body jsonBody: String
    ): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_album/getImages")
    fun getYourUploadedImagesByAlbum(
        @Header("Sessionid") sessionId: String,
        @Body jsonBody: String
    ): Deferred<Profile>


    /**
     * profile apis
     */

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/updateCoverPhoto")
    fun updateCoverPhoto(
        @Header("Sessionid") sessionId: String

    ): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/updateAvatar")
    fun updateProfilePic(
        @Header("Sessionid") sessionId: String
    ): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/updateAddress")
    fun updateAddress(@Body updateAddressJson: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/addMarchantProfile")
    fun addBasicProfileInfo(
        @Header("Sessionid") sessionId: String,
        @Body updateAddressJson: String
    ): Deferred<Profile>


    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/addReservation")
    fun orderMerchant(
        @Header("Sessionid") sessionId: String,
        @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/swapBooking")
    fun swapBooking(
        @Header("Sessionid") sessionId: String,
        @Body json: String): Deferred<Profile>


    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/getActiveBookings")
    fun currentBooking(
        @Header("Sessionid") sessionId: String,
        @Body json: String): Deferred<Profile>


    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/getPastBookings")
    fun pastBooking(
        @Header("Sessionid") sessionId: String,
        @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/getNotifications")
    fun getNotificationsList(
        @Header("Sessionid") sessionId: String,
        @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/getMessages")
    fun getNotificationsMessages(
        @Header("Sessionid") sessionId: String,
        @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/acceptRejectSwapOrder")
    fun acceptOrRejectSwapOrder(
        @Header("Sessionid") sessionId: String,
        @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users/changePassword")
    fun resetPassword(
        @Header("Sessionid") sessionId: String,
        @Body json: String): Deferred<Profile>

    /**
     * Services
     */
    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_services/getMarchantCategories")
    fun getMerchantCategories(
        @Header("Sessionid") sessionId: String, @Body jsonString: String):Deferred<Profile>


    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_services/addServices")
    fun addServices(@Header("Sessionid") sessionId: String,@Body toString: String):Deferred<Profile>


    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_services/addAgent")
    fun addAgent(@Header("Sessionid") sessionId: String, @Body toString: String):Deferred<Profile>


    /**
     * SErvices inside dropdown in dialog
     */
    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_services/getCategoryServices")
    fun getServices(@Header("Sessionid") sessionId: String,@Body json: String):Deferred<Profile>

    /**
     * update service
     */
    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_services/updateServices")
    fun updateService(@Header("Sessionid") sessionId: String,@Body json: String):Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_services/deleteServices")
    fun deleteService(@Header("Sessionid") sessionId: String,@Body json: String):Deferred<Profile>

    /**************************
     * active Orders          *
     *************************/
    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_orders/getMarchantActiveOrders")
    fun activeOrders(@Header("Sessionid") sessionId: String,@Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_orders/getMarchantCompltedOrders")
    fun completeOrders(@Header("Sessionid") sessionId: String,@Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_services/getServiceAgainstCategory")
    fun getServicesAgainstCategory(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_orders/cancelOrders")
    fun cancelOrders(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_orders/getMarchantRescheduleOrders")
    fun rescheduleOrders(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_orders/startOrder")
    fun startOrder(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_orders/endOrder")
    fun endOrder(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>


    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_orders/orderDetails")
    fun orderDetails(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Marchant_orders/marchantDashboard")
    fun getDashboard(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/like_photos")
    fun isLikePhoto(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("users_api/Home/getLikeCommentCounts")
    fun imageCommentsCount(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>


    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Discovery/updateDiscovery")
    fun updateBusinessLocation(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>

    @Headers("Content-Type:application/json", "Accesskey:b8d7996ea3557791aba04cf51a0654b2")
    @POST("Discovery/getDiscovery")
    fun getBusinessLocation(@Header("Sessionid") sessionId: String, @Body json: String): Deferred<Profile>
//    @POST("users/updateAddress")
//    fun updateAddress(
//        @Header("Sessionid") sessionId: String
//    ):Deferred<Profile>
}