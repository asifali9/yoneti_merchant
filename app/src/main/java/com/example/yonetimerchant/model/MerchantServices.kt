package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

class MerchantServices (
    @SerializedName("id") var serviceId:String,
    @SerializedName("user_id") var userId:String,
    @SerializedName("service_title") var serviceTitle:String,
    @SerializedName("service_charged") var serviceCharged:String,
    @SerializedName("estimated_time") var estimatedTime:String,
    @SerializedName("category_id") var categoryId:String
    )
{

}