package com.thepop.android.data.repository

import com.thepop.android.data.model.user.KakaoLoginResponse
import com.thepop.android.data.service.UserService
import com.thepop.android.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {


    override suspend fun kakaoLogin(accessToken: String): KakaoLoginResponse {
        return userService.kakaoLogin(accessToken)
    }

    override suspend fun reissueToken(refreshToken: String): KakaoLoginResponse {
        return userService.reissueToken(refreshToken)
    }

}