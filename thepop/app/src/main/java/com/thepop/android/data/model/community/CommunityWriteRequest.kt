package com.thepop.android.data.model.community

import com.google.gson.annotations.SerializedName

data class CommunityWriteRequest(
    @SerializedName("data") val data: CommunityWriteData,
) {
    data class CommunityWriteData(
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("categoryCode") val categoryCode: String,
    )
}
