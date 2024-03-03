package com.thepop.android.data.model.community

import com.google.gson.annotations.SerializedName
data class CommunityWriteVoteRequest(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("categoryCode") val categoryCode: String,
    @SerializedName("votes") val votes: List<String>,
)
