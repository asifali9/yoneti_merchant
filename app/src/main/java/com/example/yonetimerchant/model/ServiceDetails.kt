package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class ServiceDetails(
    @SerializedName("id")
    var businessId:String,// this is merchantId
    @SerializedName("name")
    var businessName: String,
    @SerializedName("address")
    var address: String ,
    @SerializedName("image")
    var imagePath: String,
    @SerializedName("category")
    var businessType: String
)
{
}