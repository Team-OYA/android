package com.thepop.android.data.model.community

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CommunityWriteRequest(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("categoryCode") val categoryCode: String,
)