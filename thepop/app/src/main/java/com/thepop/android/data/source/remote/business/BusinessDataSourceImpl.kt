package com.thepop.android.data.source.remote.business

import com.thepop.android.data.model.business.BusinessResponse
import com.thepop.android.data.service.BusinessService
import javax.inject.Inject

class BusinessDataSourceImpl @Inject constructor(
    private val businessService: BusinessService
) : BusinessDataSource {
    override suspend fun getBusiness(userId: Int): BusinessResponse {
        return businessService.getBusiness(userId)
    }
}