package com.thepop.android.data.source

interface LocalDataSource {
    fun setAccessToken(token: String?)
    fun getAccessToken(): String?
    fun setRefreshToken(token: String)
    fun getRefreshToken(): String?
    fun setUserId(userId: String)
    fun getUserId(): String?
    fun clear()

    fun removeAccessToken()
}