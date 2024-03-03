package com.thepop.android.data.model.popup

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PopupDetailResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: PopupDetailData
) {

    @Parcelize
    data class PopupDetailData(
        @SerializedName("popupId") val popupId: Int,
        @SerializedName("planId") val planId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("pulledDate") val pulledDate: String,
        @SerializedName("category") val category: PopupListResponse.PopupList.Popup.Category,
        @SerializedName("openDate") val openDate: String,
        @SerializedName("closeDate") val closeDate: String,
        @SerializedName("collected") val collected: Boolean
    )
}
