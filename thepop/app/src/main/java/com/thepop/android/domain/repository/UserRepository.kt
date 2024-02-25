package com.thepop.android.domain.repository

import com.thepop.android.data.model.user.KakaoLoginResponse

interface UserRepository {
    suspend fun kakaoLogin(accessToken: String): KakaoLoginResponse

    suspend fun reissueToken(refreshToken: String): KakaoLoginResponse

}