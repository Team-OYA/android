package com.thepop.android.data.source

import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val authLocalPreferences: LocalDataSource
) : LocalDataSource {

        override fun setAccessToken(token: String) {
            authLocalPreferences.setAccessToken(token)
        }

        override fun getAccessToken(): String? {
            return authLocalPreferences.getAccessToken()
        }

        override fun setRefreshToken(token: String) {
            authLocalPreferences.setRefreshToken(token)
        }

        override fun getRefreshToken(): String? {
            return authLocalPreferences.getRefreshToken()
        }

        override fun setUserId(userId: String) {
            authLocalPreferences.setUserId(userId)
        }

        override fun getUserId(): String? {
            return authLocalPreferences.getUserId()
        }

        override fun clear() {
            authLocalPreferences.clear()
        }

        override fun removeAccessToken() {
            authLocalPreferences.removeAccessToken()
        }
}
