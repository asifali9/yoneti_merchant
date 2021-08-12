package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("total_count") var totalCount: String,
    @SerializedName("order_details") var details: String,
    @SerializedName("end_time") var endTime: String,
    @SerializedName("start_time") var startTime: String,
    @SerializedName("date") var date: String
)
{
}