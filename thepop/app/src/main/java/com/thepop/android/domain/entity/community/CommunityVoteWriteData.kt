package com.thepop.android.domain.entity.community

data class CommunityVoteWriteData(
    var title: String,
    var description: String,
    var categoryCode: String,
    var votes: List<String>,
)
