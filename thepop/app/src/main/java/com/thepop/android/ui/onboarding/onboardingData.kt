package com.thepop.android.ui.onboarding

import com.thepop.android.R

data class OnboardingData(
    val image: Int,
    val title: String,
    val content: String
)

val onboardingList = listOf(
    OnboardingData(
        image = R.drawable.img_onboarding1,
        title = "현대의 팝업들을 한눈에",
        content = "현대백화점에서 진행하는 팝업스토어 \n" +
                "리스트를 확인하고 상세 정보도 확인하세요. "
    ),
    OnboardingData(
        image = R.drawable.img_onboarding2,
        title = "내가 만들어가는 팝업스토어",
        content = "원하는 팝업스토어 컨셉이나 브랜드에 대해 \n" +
                "자유롭게 소통하고 다른 사용자의 반응도 확인하세요"
    ),
    OnboardingData(
        image = R.drawable.img_onboarding3,
        title = "취향저격 팝업 추천",
        content = "간단한 문답을 통해 내 취향을 확인하고,\n" +
                "내 취향에 맞는 팝업스토어나 브랜드를 추천해드려요."
    )
)