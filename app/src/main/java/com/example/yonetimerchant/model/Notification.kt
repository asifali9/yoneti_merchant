package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class Notification (
    @SerializedName("id") var info:String,
    @SerializedName("name") var time:String,
    )
{
}