package com.thepop.android.data.source.remote.user

import com.thepop.android.data.model.user.KakaoLoginResponse

interface UserDataSource {
    suspend fun kakaoLogin(accessToken: String): KakaoLoginResponse

    suspend fun reissueToken(refreshToken: String): KakaoLoginResponse

}