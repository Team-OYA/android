package com.thepop.android.data.repository

import android.util.Log
import com.thepop.android.data.model.BaseResponse
import com.thepop.android.data.model.community.CommunityDetailResponse
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.data.model.community.CommunityWriteRequest
import com.thepop.android.data.model.community.CommunityWriteVoteRequest
import com.thepop.android.data.service.CommunityService
import com.thepop.android.domain.repository.CommunityRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val communityService: CommunityService
) : CommunityRepository {

    override suspend fun getCommunityDetail(postId: Int): CommunityDetailResponse {
        return communityService.getCommunityDetail(postId)
    }

    override suspend fun createCommunityPostVote(
        type: String,
        data: CommunityWriteVoteRequest,
        images: List<MultipartBody.Part>
    ): BaseResponse {
        return communityService.createCommunityPostVote(type, data, images)
    }

    override suspend fun createCommunityPost(
        type: String,
        data: CommunityWriteRequest,
        images: List<MultipartBody.Part>
    ): BaseResponse {
        return communityService.createCommunityPost(type, data, images)
    }

    override suspend fun checkVote(voteId: Int): BaseResponse {
        return communityService.checkVote(voteId)
    }

    override suspend fun cancelVote(voteId: Int): BaseResponse {
        return communityService.cancelVote(voteId)
    }

    override suspend fun deleteCommunityPost(postId: Int): BaseResponse {
        return communityService.deleteCommunityPost(postId)
    }

    override suspend fun getCommunityList(type: String, pageNo: Int, amount: Int): CommunityListResponse {
        return communityService.getCommunityList(type, pageNo, amount)
    }

    override suspend fun scrapCommunityPost(postId: Int): BaseResponse {
        return communityService.scrapCommunityPost(postId)
    }
}