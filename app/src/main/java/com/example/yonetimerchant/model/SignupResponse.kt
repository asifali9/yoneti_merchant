package com.example.yoneti.model

import com.example.yoneti.base.BaseResult
import com.example.yonetimerchant.base.BaseModel
import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("result")
    var result: BaseResult
) : BaseModel() {
}