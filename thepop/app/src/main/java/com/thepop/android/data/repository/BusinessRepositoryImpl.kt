package com.thepop.android.data.repository

import com.thepop.android.data.model.business.BusinessResponse
import com.thepop.android.data.service.BusinessService
import com.thepop.android.domain.repository.BusinessRepository
import javax.inject.Inject

class BusinessRepositoryImpl @Inject constructor(
    private val businessService: BusinessService
) : BusinessRepository {
    override suspend fun getBusiness(userId: Int): BusinessResponse {
        return businessService.getBusiness(userId)
    }
}