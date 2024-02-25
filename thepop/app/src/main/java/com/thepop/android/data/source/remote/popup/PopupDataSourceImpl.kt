package com.thepop.android.data.source.remote.popup

import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.data.service.PopupService
import javax.inject.Inject

class PopupDataSourceImpl @Inject constructor(
    private val popupService: PopupService
    ) : PopupDataSource {
    override suspend fun getPopupList(sort: String, pageNo: Int, amount: Int): PopupListResponse {
        return popupService.getPopupList(sort, pageNo, amount)
    }

}