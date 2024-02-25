package com.thepop.android.data.model.community

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CommunityDetailResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: CommunityDetailData
) {

    @Parcelize
    data class CommunityDetailData(
        @SerializedName("writeId") val writeId: Int,
        @SerializedName("nickname") val nickname: String,
        @SerializedName("email") val email: String,
        @SerializedName("userType") val userType: String,
        @SerializedName("communityId") val communityId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("communityType") val communityType: String,
        @SerializedName("category") val category: String,
        @SerializedName("categoryDescription") val categoryDescription: String,
        @SerializedName("createdDate") val createdDate: String,
        @SerializedName("modifiedDate") val modifiedDate: String,
        @SerializedName("countView") val countView: Int,
        @SerializedName("voteResponseList") val voteResponseList: Any?,
        @SerializedName("imageList") val imageList: List<String>
    )
}
