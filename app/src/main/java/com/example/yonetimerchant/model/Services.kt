package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

class Services (
    @SerializedName("id") var serviceId:String,
    @SerializedName("name") var serviceName:String
    )
{
    override fun toString(): String {
        return serviceName
    }
}