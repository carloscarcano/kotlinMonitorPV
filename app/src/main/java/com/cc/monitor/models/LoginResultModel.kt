package com.cc.monitor.models

import com.google.gson.annotations.SerializedName

data class LoginResultModel(
    @SerializedName("login") val login: Boolean,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("estado") val estado: String)