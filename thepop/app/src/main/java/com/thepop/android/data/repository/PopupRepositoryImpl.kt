package com.thepop.android.data.repository

import com.thepop.android.data.source.remote.popup.PopupDataSource
import com.thepop.android.domain.repository.PopupRepository
import javax.inject.Inject

class PopupRepositoryImpl @Inject constructor(
    private val popupDataSource: PopupDataSource
    ) : PopupRepository {
    override suspend fun getPopupList(sort: String, pageNo: Int, amount: Int) {
        popupDataSource.getPopupList(sort, pageNo, amount)
    }
}