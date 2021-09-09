package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("category_id")
    var categoryId: String,
    @SerializedName("category_name")
    var serviceCategoryName: String,
    @SerializedName("marchant")
    var listOfServiceDetails: ArrayList<ServiceDetails>,
    /**
     * Service Spinner items
     */
    @SerializedName("service_id") //common field
    var serviceId: String,
    @SerializedName("name")
    var serviceName: String,
    @SerializedName("service_charges")
    var serviceCharges: String,
    @SerializedName("estimated_time")
    var estimatedTime: String,

    @SerializedName("agent_id")
    var agentId: String,
    @SerializedName("agent_name")
    var agentName: String,
    @SerializedName("agent_fees")
    var agentCharges: String,


    /**
     * For getting business locations
     */
    @SerializedName("id")
    var id: String,
    @SerializedName("user_id")
    var userId: String,
    @SerializedName("country_name")
    var country: String,
    @SerializedName("city_name")
    var city: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("zip_code")
    var zipcode: String,
    @SerializedName("business_detail")
    var businessDetail: String,
    @SerializedName("business_start_time")
    var openingTime: String,
    @SerializedName("business_close_time")
    var closeTime: String,
    @SerializedName("business_range")
    var businessRange: String,
    @SerializedName("business_radius")
    var businessRadius: String,
    @SerializedName("is_curr_location")
    var isCurrent: String,
    @SerializedName("current_location_lat")
    var lat: String,
    @SerializedName("current_location_long")
    var lng: String,
) {
}