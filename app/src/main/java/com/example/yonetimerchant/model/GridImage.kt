package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

class GridImage (
    @SerializedName("id") var id:String,
    @SerializedName("name") var imagePath:String,
    @SerializedName("user_id") var userId:String,
    @SerializedName("album_id") var albumId:String
    )
{
}