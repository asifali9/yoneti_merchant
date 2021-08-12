package com.example.yonetimerchant.model

import com.google.gson.annotations.SerializedName

class QueueOrders() {
    @SerializedName("user_name")
    var userName: String? = null

    @SerializedName("marchant_rating")
    var rating: String? = null

    @SerializedName("user_pic")
    var picUrl: String? = null

    @SerializedName("order_id")
    var orderId: String? = null

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
}