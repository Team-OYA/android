package com.thepop.android.data.model.popup

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PopupListResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: PopupList
) {
    data class PopupList(
        @SerializedName("popups") val popups: List<Popup>
    ) {

        @Parcelize
        data class Popup(
            @SerializedName("planId") val planId: Int,
            @SerializedName("title") val title: String,
            @SerializedName("description") val description: String,
            @SerializedName("pulledDate") val pulledDate: String,
            @SerializedName("openDate") val openDate: String,
            @SerializedName("closeDate") val closeDate: String,
            @SerializedName("thumbnail") val thumbnail: String,
            @SerializedName("category") val category: Category
        ) {
            @Parcelize
            data class Category(
                @SerializedName("code") val code: String,
                @SerializedName("description") val description: String
            )
        }
    }
}
