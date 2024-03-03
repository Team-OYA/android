package com.thepop.android.data.repository

import com.thepop.android.data.model.BaseResponse
import com.thepop.android.data.model.popup.PopupDetailResponse
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.data.service.PopupService
import com.thepop.android.data.service.UserService
import com.thepop.android.data.source.remote.popup.PopupDataSource
import com.thepop.android.domain.repository.PopupRepository
import javax.inject.Inject

class PopupRepositoryImpl @Inject constructor(
    private val popupService: PopupService
    ) : PopupRepository {
    override suspend fun getPopupList(sort: String, pageNo: Int, amount: Int): PopupListResponse {
        return popupService.getPopupList(sort, pageNo, amount)
    }

    override suspend fun getPopupDetail(id: Int): PopupDetailResponse {
        return popupService.getPopupDetail(id)
    }

    override suspend fun getPopupRecommend(pageNo: Int, amount: Int): PopupListResponse {
        return popupService.getPopupRecommend(pageNo, amount)
    }

    override suspend fun scrapPopup(popupId: Int): BaseResponse {
        return popupService.scrapPopup(popupId)
    }

}