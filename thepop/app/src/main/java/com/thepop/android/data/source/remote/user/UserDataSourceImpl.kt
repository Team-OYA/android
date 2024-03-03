package com.thepop.android.data.source.remote.user

import com.thepop.android.data.model.user.KakaoLoginResponse
import com.thepop.android.data.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserDataSource {

        override suspend fun kakaoLogin(accessToken: String): KakaoLoginResponse {
            return userService.kakaoLogin(accessToken)
        }

        override suspend fun reissueToken(refreshToken: String): KakaoLoginResponse {
            return userService.reissueToken(refreshToken)
        }

}