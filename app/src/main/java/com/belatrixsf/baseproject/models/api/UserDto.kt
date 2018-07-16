package com.belatrixsf.baseproject.models.api

import com.belatrixsf.baseproject.managers.network.annotations.InnerKey
import com.google.gson.annotations.SerializedName

@InnerKey("customer")
data class UserDto(
        @SerializedName("id") var id: Long? = null,
        @SerializedName("firstname") var firstname: String,
        @SerializedName("lastname") var lastname: String,
        @SerializedName("email") var email: String,
        @SerializedName("password") var password: String
)