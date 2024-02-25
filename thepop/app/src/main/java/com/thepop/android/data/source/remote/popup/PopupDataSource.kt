package com.thepop.android.data.source.remote.popup

import com.thepop.android.data.model.BaseResponse
import com.thepop.android.data.model.popup.PopupDetailResponse
import com.thepop.android.data.model.popup.PopupListResponse

interface PopupDataSource {


    suspend fun getPopupList(sort: String, pageNo: Int, amount: Int): PopupListResponse

    suspend fun getPopupDetail(popupId: Int): PopupDetailResponse

    suspend fun getPopupRecommend(pageNo: Int, amount: Int): PopupListResponse

    suspend fun scrapPopup(popupId: Int): BaseResponse
}