package com.thepop.android.data.service

import com.thepop.android.data.model.BaseResponse
import com.thepop.android.data.model.popup.PopupDetailResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import com.thepop.android.data.model.popup.PopupListResponse as PopupListResponse

interface PopupService {

    @GET("popups")
    suspend fun getPopupList(
        @Query("sort") sort: String,
        @Query("pageNo") pageNo: Int,
        @Query("amount") amount: Int
    ): PopupListResponse

    @GET("popups/{popupId}")
    suspend fun getPopupDetail(
        @Path("popupId") popupId: Int
    ): PopupDetailResponse

    @GET("popups/recommended")
    suspend fun getPopupRecommend(
        @Query("pageNo") pageNo: Int,
        @Query("amount") amount: Int
    ): PopupListResponse

    @POST("popups/{popupId}/collections")
    suspend fun scrapPopup(
        @Path("popupId") popupId: Int
    ): BaseResponse

}