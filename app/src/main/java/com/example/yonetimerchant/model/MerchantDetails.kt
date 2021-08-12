package com.example.yoneti.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * this class is used for mutliple screen as model
 */


data class MerchantDetails(
    @SerializedName("id")
    var serviceId: String,
    @SerializedName("avatar")
    var imgPath: String,
    @SerializedName("brand_name")
    var brandName: String,
    @SerializedName("fullname")
    var serviceProviderName: String,
    @SerializedName("user_type")
    var userType: String,
    @SerializedName("business_type")
    var businessType: String,
    @SerializedName("business_details")
    var businessDetails: String,
    @SerializedName("is_bookmark")
    var isBookMarked: Boolean,

    /**
     * get Merchant Response
     */
    @SerializedName("name")
    var merchantName: String,
    @SerializedName("phone_num")
    var phoneNumber: String,
    @SerializedName("website")
    var website: String,
//    @SerializedName("address")
//    var address: String,
    @SerializedName("dob")
    var dob: String,
//    @SerializedName("avatar")
//    var avatar: String,
    @SerializedName("cover_photo")
    var coverPhoto: String,
    @SerializedName("total_shares")
    var sharesCount: String,
    @SerializedName("total_reviews")
    var reviewsCount: String,
    @SerializedName("total_photos")
    var photosCount: String,
    @SerializedName("total_bookmarks")
    var bookmarkCount: String,
    @SerializedName("average_cost")
    var avgCost: String,


    @SerializedName("address")
    var businessAddress: String,
    @SerializedName("rating")
    var businessRating: String,
    @SerializedName("business_start_time")
    var businessStartTime: String,
    @SerializedName("business_close_time")
    var businessCloseTime: String = "",
    @SerializedName("orders") var listOfOrders: ArrayList<Orders>,
    @SerializedName("reviews") var listOfReviews: ArrayList<Reviews>,
    @SerializedName("nearby") var listOfNearby: ArrayList<Nearby>,
) {
}