package com.thepop.android.data.source.remote

import com.thepop.android.data.model.user.KakaoLoginResponse
import com.thepop.android.data.model.user.PopupListResponse
import com.thepop.android.data.service.UserService
import javax.inject.Inject

interface UserDataSource {
    suspend fun kakaoLogin(accessToken: String): KakaoLoginResponse

    suspend fun reissueToken(refreshToken: String): KakaoLoginResponse

    suspend fun getPopups(sort: String, pageNo: Int, amount: Int): PopupListResponse

}