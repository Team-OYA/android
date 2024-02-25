package com.thepop.android.domain.repository

interface UserRepository {
    suspend fun kakaoLogin(accessToken: String)

    suspend fun reissueToken(refreshToken: String)

    suspend fun getPopups(sort: String, pageNo: Int, amount: Int)
}