package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class MerchantTimeSlots(

    @SerializedName("order_time")
    var orderTimeSlot: String
) {
}