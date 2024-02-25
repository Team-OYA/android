package com.thepop.android.data.repository

import android.util.Log
import com.thepop.android.data.source.remote.UserDataSource
import com.thepop.android.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {


    override suspend fun kakaoLogin(accessToken: String) {
        userDataSource.kakaoLogin(accessToken)
    }

    override suspend fun reissueToken(refreshToken: String) {
        userDataSource.reissueToken(refreshToken)
    }

    override suspend fun getPopups(sort: String, pageNo: Int, amount: Int) {
        userDataSource.getPopups(sort, pageNo, amount)
    }
}