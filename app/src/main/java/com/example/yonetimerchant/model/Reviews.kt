package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class Reviews(
    @SerializedName("total_count") var totalReviews: String,
    @SerializedName("name") var userName: String,
    @SerializedName("avatar") var avatar: String,
    @SerializedName("comments") var comment: String,
    @SerializedName("date") var commentDate: String
)
{
}