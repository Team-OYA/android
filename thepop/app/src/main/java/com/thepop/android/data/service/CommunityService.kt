package com.thepop.android.data.service

import com.thepop.android.data.model.BaseResponse
import com.thepop.android.data.model.community.CategoryResponse
import com.thepop.android.data.model.community.CommunityDetailResponse
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.data.model.community.CommunityWriteRequest
import com.thepop.android.data.model.community.CommunityWriteVoteRequest
import okhttp3.MultipartBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface CommunityService {

    @GET("communities/{postId}")
    suspend fun getCommunityDetail(
        @Path("postId") postId: Int
    ): CommunityDetailResponse

    @Multipart
    @POST("communities")
    suspend fun createCommunityPostVote(
        @Query("type") type: String = "vote",
        @Part("data") data: CommunityWriteVoteRequest?,
        @Part images: List<MultipartBody.Part>  // 여러 이미지를 보내는 데에는 List를 사용합니다.
    ): BaseResponse

    @Multipart
    @POST("communities")
    suspend fun createCommunityPost(
        @Query("type") type: String = "basic",
        @Part("data") data: CommunityWriteRequest?,
        @Part images: List<MultipartBody.Part>  // 여러 이미지를 보내는 데에는 List를 사용합니다.
    ): BaseResponse

    @POST("votes/{voteId}/check")
    suspend fun checkVote(
        @Path("voteId") voteId: Int
    ): BaseResponse

    @DELETE("votes/{voteId}/check")
    suspend fun cancelVote(
        @Path("voteId") voteId: Int
    ): BaseResponse

    @DELETE("communities/{postId}")
    suspend fun deleteCommunityPost(
        @Path("postId") postId: Int
    ): BaseResponse

    @GET("communities")
    suspend fun getCommunityList(
        @Query("type") type: String,
        @Query("pageNo") pageNo: Int,
        @Query("amount") amount: Int
    ): CommunityListResponse

    @GET("communities/{postId}/collections")
    suspend fun scrapCommunityPost(
        @Path("postId") postId: Int
    ): BaseResponse

    @GET("plans/categories")
    suspend fun getCategoryList(): CategoryResponse

}
