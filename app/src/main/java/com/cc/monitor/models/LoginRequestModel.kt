package com.cc.monitor.models

import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("usr") val usr: String,
    @SerializedName("pwd") val pwd: String)
