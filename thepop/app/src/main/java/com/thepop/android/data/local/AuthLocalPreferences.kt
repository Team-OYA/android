package com.thepop.android.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthLocalPreferences @Inject constructor(
    @ApplicationContext private val context: Context
){
    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val USER_ID = "USER_ID"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences("ACCESS_TOKEN", Context.MODE_PRIVATE)

    fun setAccessToken(token: String) = prefs.edit().putString(ACCESS_TOKEN, token).apply()

    fun getAccessToken(): String? = prefs.getString(ACCESS_TOKEN, "")

    fun setRefreshToken(token: String) = prefs.edit().putString(REFRESH_TOKEN, token).apply()

    fun getRefreshToken(): String? = prefs.getString(REFRESH_TOKEN, "")

    fun setUserId(userId: String) = prefs.edit().putString(USER_ID, userId).apply()

    fun getUserId(): String? = prefs.getString(USER_ID, "")

    fun clear() = prefs.edit().clear().apply()

}