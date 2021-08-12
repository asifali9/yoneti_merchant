package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

/**
 * same class is used for recent orders in dashboard
 */
class ActiveOrder() {
    @SerializedName("user_name")
    var userName: String? = null

    @SerializedName("marchant_rating")
    var merchantRating: String? = null

    @SerializedName("user_pic")
    var userPic: String? = null

    @SerializedName("order_id")
    var categoryId: String? = null

    @SerializedName("marchant_id")
    var merchantId: String? = null

    @SerializedName("date")
    var date: String? = null

    @SerializedName("start_time")
    var startTime: String? = null

    @SerializedName("end_time")
    var endTime: String? = null

    @SerializedName("order_details")
    var orderDetails: String? = null

    @SerializedName("rating_count")
    var ratingCount: String? = null

    @SerializedName("customer_name")
    var customerName: String? = null

//    @SerializedName("user_pic")
//    var customerImages: String? = null

}