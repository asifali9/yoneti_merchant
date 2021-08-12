package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class Messages(
    @SerializedName("user_id")
    var userId: String,
    @SerializedName("user_name")
    var userName: String,
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("message_date")
    var messageDate: String

) {
}