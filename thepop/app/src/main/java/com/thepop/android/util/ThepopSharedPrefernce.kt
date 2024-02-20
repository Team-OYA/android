package com.thepop.android.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ThepopSharedPrefernce @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val USER_ID = "USER_ID"
    }

    fun setAccessToken(token: String) {
        val prefs: SharedPreferences = context.getSharedPreferences("ACCESS_TOKEN", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(ACCESS_TOKEN, token)
        editor.apply()
    }

    fun getAccessToken(): String? {
        val prefs: SharedPreferences = context.getSharedPreferences("ACCESS_TOKEN", Context.MODE_PRIVATE)
        return prefs.getString(ACCESS_TOKEN, null)
    }

    fun setRefreshToken(token: String) {
        val prefs: SharedPreferences = context.getSharedPreferences("REFRESH_TOKEN", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(REFRESH_TOKEN, token)
        editor.apply()
    }

    fun getRefreshToken(): String? {
        val prefs: SharedPreferences = context.getSharedPreferences("REFRESH_TOKEN", Context.MODE_PRIVATE)
        return prefs.getString(REFRESH_TOKEN, null)
    }

    fun setUserId(userId: String) {
        val prefs: SharedPreferences = context.getSharedPreferences("USER_ID", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(USER_ID, userId) // 사용자 ID 저장
        editor.apply()
    }


    fun getUserId(): String? {
        val prefs: SharedPreferences = context.getSharedPreferences("USER_ID", Context.MODE_PRIVATE)
        return prefs.getString(USER_ID, null)
    }

}