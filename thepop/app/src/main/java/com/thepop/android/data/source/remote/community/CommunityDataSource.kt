package com.thepop.android.data.source.remote.community

import com.thepop.android.data.model.BaseResponse
import com.thepop.android.data.model.community.CommunityDetailResponse
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.data.model.community.CommunityWriteRequest
import com.thepop.android.data.model.community.CommunityWriteVoteRequest
import okhttp3.MultipartBody

interface CommunityDataSource {

    suspend fun getCommunityDetail(postId: Int): CommunityDetailResponse

    suspend fun createCommunityPostVote(
        type: String, data: CommunityWriteVoteRequest, images: List<MultipartBody.Part>): BaseResponse

    suspend fun createCommunityPost(
        type: String, data: CommunityWriteRequest, images: List<MultipartBody.Part>): BaseResponse

    suspend fun checkVote(voteId: Int): BaseResponse

    suspend fun cancelVote(voteId: Int): BaseResponse

    suspend fun deleteCommunityPost(postId: Int): BaseResponse

    suspend fun getCommunityList(type: String, pageNo: Int, amount: Int): CommunityListResponse

    suspend fun scrapCommunityPost(postId: Int): BaseResponse

}
