package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class Nearby(
    @SerializedName("id") var id:String,
    @SerializedName("name") var serviceName:String,
    @SerializedName("address") var address:String,
    @SerializedName("image") var imgPath:String,
    @SerializedName("category") var serviceCategory:String) {
}