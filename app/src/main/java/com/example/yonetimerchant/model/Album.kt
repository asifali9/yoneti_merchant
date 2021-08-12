package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

class Album (
    @SerializedName("id") var id:String,
    @SerializedName("name") var albumName:String,
    @SerializedName("user_id") var userId:String,
    @SerializedName("img_cnt") var tabCount:String
    )
{
}