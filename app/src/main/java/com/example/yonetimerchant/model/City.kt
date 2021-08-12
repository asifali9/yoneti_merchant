package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class City(
    var flag:String ="path",
    @SerializedName("country_name")
    var countryName:String,
    @SerializedName("country_id")
    var countryId:String,
    @SerializedName("id")
    var cityId:String,
    @SerializedName("name")
    var city:String,
    @SerializedName("city_lat")
    var cityLat:String,
    @SerializedName("city_long")
    var cityLong:String,
){
}