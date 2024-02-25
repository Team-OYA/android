package com.thepop.android.domain.repository

interface UserRepository {
    suspend fun kakaoLogin(accessToken: String)

    suspend fun reissueToken(refreshToken: String)

}