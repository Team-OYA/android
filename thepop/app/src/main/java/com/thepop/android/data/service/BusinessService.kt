package com.thepop.android.data.service

import com.thepop.android.data.model.business.BusinessResponse

interface BusinessService {

    suspend fun getBusiness(userId: Int): BusinessResponse
}