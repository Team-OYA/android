package com.thepop.android.data.service


import com.thepop.android.data.model.user.KakaoLoginResponse
import com.thepop.android.data.model.popup.PopupListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("oauth/login")
    suspend fun kakaoLogin(
        @Body accessToken: String
    ): KakaoLoginResponse

    @POST("oauth/reissue")
    suspend fun reissueToken(
        @Body refreshToken: String
    ): KakaoLoginResponse

}