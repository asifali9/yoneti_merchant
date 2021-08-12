package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class TopYonetiPhotos(
    @SerializedName("name")
    var title: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("image")
    var imageUrl: String,
    @SerializedName("total_reviews")
    var reviews: String,
    @SerializedName("total_photos")
    var photos: String,
    @SerializedName("is_follow")
    var isFollowing: Boolean = false,
    @SerializedName("rating_category")
    var ratingCategory: String,
    @SerializedName("rank")
    var rank: String


) {


}