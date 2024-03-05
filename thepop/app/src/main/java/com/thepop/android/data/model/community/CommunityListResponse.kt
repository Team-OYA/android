package com.thepop.android.data.model.community

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CommunityListResponse(
    @SerializedName("code") val code: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: CommunityDetailResponseList
) {
    data class CommunityDetailResponseList(
        @SerializedName("communityDetailResponseList") var communityDetailResponseList: List<CommunityDetail>
    ) {
        @Parcelize
        data class CommunityDetail(
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
            @SerializedName("voteResponseList") val voteResponseList: List<VoteResponse>,
            @SerializedName("imageList") val imageList: List<String>,
            @SerializedName("profileUrl") val profileUrl: String,
        ) {
            @Parcelize
            data class VoteResponse(
                @SerializedName("vote_id") val voteId: Int,
                @SerializedName("content") val content: String,
                @SerializedName("voteCreatedDate") val voteCreatedDate: String,
                @SerializedName("voteSum") val voteSum: Int,
                @SerializedName("checked") val checked: Boolean
            )
        }
    }
}
