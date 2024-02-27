package com.thepop.android.data.model.community

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CommunityWriteVoteRequest(
    @SerializedName("data") val data: CommunityWriteData,
) {
    @Parcelize
    data class CommunityWriteData(
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("categoryCode") val categoryCode: String,
        @SerializedName("votes") val votes: List<String>,
    )
}
