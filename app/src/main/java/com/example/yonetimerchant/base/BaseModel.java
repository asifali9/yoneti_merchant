package com.example.yonetimerchant.base;

import com.google.gson.annotations.SerializedName;

public class BaseModel {
    @SerializedName("status_code")
    public boolean status;
    @SerializedName("message")
    public String message;
}
