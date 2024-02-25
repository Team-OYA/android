package com.thepop.android.data.source

import com.thepop.android.data.local.AuthLocalPreferences
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val authLocalPreferences: AuthLocalPreferences
) : LocalDataSource {

        override fun setAccessToken(token: String?) {
            if (token != null) {
                authLocalPreferences.setAccessToken(token)
            }
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
