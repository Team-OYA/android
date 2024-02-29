package com.thepop.android.domain.repository

import com.thepop.android.data.model.business.BusinessResponse

interface BusinessRepository {
    suspend fun getBusiness(userId: Int): BusinessResponse
}