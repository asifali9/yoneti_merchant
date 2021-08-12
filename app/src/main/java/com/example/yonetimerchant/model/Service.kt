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
    var agentCharges: String
) {
}