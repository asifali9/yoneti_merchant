package com.example.yoneti.model

import com.google.gson.annotations.SerializedName

class Categories {
    @SerializedName("id")
    var categoryId:String? = null

    @SerializedName("name")
    var categoryName:String = "testing"

}