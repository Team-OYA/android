package com.thepop.android.data.source.remote.business

import com.thepop.android.data.model.business.BusinessResponse

interface BusinessDataSource {
    suspend fun getBusiness(userId: Int): BusinessResponse
}