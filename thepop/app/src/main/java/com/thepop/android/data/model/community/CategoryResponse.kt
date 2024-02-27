package com.thepop.android.data.model.community

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CategoryResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: CategoryList
) {

    data class CategoryList(
        @SerializedName("categories") val categories: List<CategoryData>
    ) {
        @Parcelize
        data class CategoryData(
            @SerializedName("code") val code: String,
            @SerializedName("description") val description: String
        )
    }

}
