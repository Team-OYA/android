package com.thepop.android.domain.repository

interface PopupRepository {
    suspend fun getPopupList(sort: String, pageNo: Int, amount: Int)
}