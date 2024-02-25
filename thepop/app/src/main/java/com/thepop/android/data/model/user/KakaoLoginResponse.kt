package com.thepop.android.data.model.user

data class KakaoLoginResponse(
    val code: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val grantType: String,
        val accessToken: String,
        val refreshToken: String
    )
}
