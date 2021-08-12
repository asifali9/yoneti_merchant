package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

class Address (
    @SerializedName("country") var country:String,
    @SerializedName("city") var city:String,
    @SerializedName("street") var street:String,
    @SerializedName("zip_code") var zipCode:String
    )
{
}