package com.thepop.android.data.source.remote.popup

import com.thepop.android.data.model.popup.PopupListResponse

interface PopupDataSource {


    suspend fun getPopupList(sort: String, pageNo: Int, amount: Int): PopupListResponse
}