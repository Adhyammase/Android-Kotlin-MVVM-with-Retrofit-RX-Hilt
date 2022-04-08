package com.ammase.androidmvvmdaggerrx.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SignResponseModel(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: Data,
    @SerializedName("message")
    var message: String
) {
    @Keep
    data class Data(
        @SerializedName("avatar")
        var avatar: Any?,
        @SerializedName("email")
        var email: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("role")
        var role: String,
        @SerializedName("token")
        var token: String,
        @SerializedName("uuid")
        var uuid: String
    )
}