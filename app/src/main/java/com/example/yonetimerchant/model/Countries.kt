package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class Countries(
    var flag:String ="path",
    @SerializedName("name")
    var countryName:String,
    @SerializedName("id")
    var countryId:String
){
}