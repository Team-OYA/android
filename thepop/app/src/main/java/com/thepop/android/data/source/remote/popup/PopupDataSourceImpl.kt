package com.thepop.android.data.source.remote.popup

import com.thepop.android.data.model.BaseResponse
import com.thepop.android.data.model.popup.PopupDetailResponse
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.data.service.PopupService
import javax.inject.Inject

class PopupDataSourceImpl @Inject constructor(
    private val popupService: PopupService
    ) : PopupDataSource {
    override suspend fun getPopupList(sort: String, pageNo: Int, amount: Int): PopupListResponse {
        return popupService.getPopupList(sort, pageNo, amount)
    }

    override suspend fun getPopupDetail(popupId: Int): PopupDetailResponse {
        return popupService.getPopupDetail(popupId)
    }

    override suspend fun getPopupRecommend(pageNo: Int, amount: Int): PopupListResponse {
        return popupService.getPopupRecommend(pageNo, amount)
    }

    override suspend fun scrapPopup(popupId: Int): BaseResponse {
        return popupService.scrapPopup(popupId)
    }

}