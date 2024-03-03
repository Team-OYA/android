package com.thepop.android.data.model.user

import com.google.gson.annotations.SerializedName

data class KakaoLoginResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("grantType") val grantType: String,
        @SerializedName("accessToken") val accessToken: String,
        @SerializedName("refreshToken") val refreshToken: String,
    )
}
