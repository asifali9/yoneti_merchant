package com.example.yoneti.base

import com.example.yoneti.model.*
import com.example.yonetimerchant.model.InProgressOrders
import com.example.yonetimerchant.model.QueueOrders
import com.google.gson.annotations.SerializedName


data class BaseResult(
    @SerializedName("session_id") var sessionId: String,
    @SerializedName("user_id") var userId: Int,
    @SerializedName("username") var userName: String,
    @SerializedName("email") var email: String,
    @SerializedName("fullname") var fullname: String,
    @SerializedName("phone_num") var phoneNumber: String,
    @SerializedName("user_type") var userType: String,
    @SerializedName("business_type") var businessType: String,
    /**
     * this field is also used as user avatar @HOmeFragment
     */
    @SerializedName("avatar") var avatar: String,
    @SerializedName("cover_photo") var coverPhoto: String,
    @SerializedName("website") var website: String,
    @SerializedName("user_bio") var userBio: String,
    @SerializedName("gender") var gender: String,

    @SerializedName("dob") var dob: String,
    @SerializedName("created_at") var createdAt: String,
    @SerializedName("device_id") var deviceId: String,
    @SerializedName("device_type") var deviceType: String,
    @SerializedName("is_push_notification") var isPushNotification: String,
    @SerializedName("is_profile_completed") var isProfileCompleted: String,

    @SerializedName("address") var address: Address,
    @SerializedName("discovery") var discoveryData: ArrayList<Service>,
    @SerializedName("reviews") var topYonetiReviews: ArrayList<TopFindrReviews>,
    @SerializedName("all_reviews") var allReviews: ArrayList<Reviews>,
    @SerializedName("photos") var topYonetiPhotos: ArrayList<TopYonetiPhotos>,
    @SerializedName("marchant_details") var merchantDetails: ArrayList<MerchantDetails>,
    @SerializedName("album_name") var album: ArrayList<Album>,
    @SerializedName("images") var gridImages: ArrayList<GridImage>,
    @SerializedName("country") var countries: ArrayList<Countries>,
    @SerializedName("time") var timeSlots: ArrayList<MerchantTimeSlots>,
    @SerializedName("city") var cities: ArrayList<City>,
//    @SerializedName("recent_uploads") var recentUploads:,
//    @SerializedName("services") var services:ArrayList<Service>,
    @SerializedName("agents") var agentService: ArrayList<AgentService>,
    @SerializedName("notification") var notificationsList: ArrayList<Notifications>,
    @SerializedName("messages") var notificationsMessages: ArrayList<Messages>,
    @SerializedName("active_booking") var activeBooking: ArrayList<Orders>,
    @SerializedName("past_booking") var pastBooking: ArrayList<Orders>,
    /**
     * merchant services
     */
    @SerializedName("services") var serviceList: ArrayList<Services>,
    /**
     * merchant Category
     */
    @SerializedName("categories") var categoryList: ArrayList<Categories>,

    @SerializedName("active_orders") var activeOrdersList: ArrayList<ActiveOrder>,
    @SerializedName("complete_orders") var completeOrdersList: ArrayList<ActiveOrder>,
    @SerializedName("marchant_services") var merchantServices: ArrayList<MerchantServices>,
    /**
     * Dashboard
     */
    @SerializedName("booking_in_due") var bookingDueIn: String,
    @SerializedName("queue_orders") var queueOrdersList: ArrayList<QueueOrders>,
    @SerializedName("inprogress_orders") var inProgressOrders: ArrayList<InProgressOrders>,
    @SerializedName("recent_orders") var recentOrdersList: ArrayList<ActiveOrder>,

    @SerializedName("marchant_rating") var merchantRating: String,
    @SerializedName("marchant_complete_orders") var completeOrdersCount: Int,
    @SerializedName("marchant_service_area") var serviceRadius: String,
    @SerializedName("likes") var singlePhotoLikesCount: String,
    @SerializedName("comments") var singlePhotoLikesCommentsCount: String,
//    @SerializedName("active_orders") var completeOrdersList:ArrayList<ActiveOrder>
//    @SerializedName("album_name") var merchantPhotos: ArrayList<MerchantPhotos>
) {
}