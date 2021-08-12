package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class Notifications(
    @SerializedName("notification_id")
    var notificationId: String,
    @SerializedName("user_id")
    var userId: String,
    @SerializedName("text")
    var notificationText: String,
    @SerializedName("notification_type")
    var notificationType: String,
    @SerializedName("ref_id")
    var refId: String,
    @SerializedName("date")
    var date: String
) {
}