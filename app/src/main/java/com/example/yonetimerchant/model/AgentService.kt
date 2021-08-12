package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

data class AgentService(
    @SerializedName("service_id")
    var serviceId: String,
    @SerializedName("agent_id")
    var agentId: String,
    @SerializedName("agent_name")
    var agentName: String,
    @SerializedName("agent_fees")
    var agentCharges: String
) {
}