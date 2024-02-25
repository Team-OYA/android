package com.thepop.android.data.service

import com.thepop.android.data.model.popup.PopupListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PopupService {

    @GET("popups")
    suspend fun getPopupList(
        @Query("sort") sort: String,
        @Query("pageNo") pageNo: Int,
        @Query("amount") amount: Int
    ): PopupListResponse

}