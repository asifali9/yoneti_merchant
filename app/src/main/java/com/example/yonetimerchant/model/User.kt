package com.example.yoneti.model

import com.example.yoneti.base.BaseResult
import com.example.yonetimerchant.base.BaseModel
import com.google.gson.annotations.SerializedName


class User(): BaseModel()
{
    @SerializedName("result")
    var result:BaseResult? = null
}