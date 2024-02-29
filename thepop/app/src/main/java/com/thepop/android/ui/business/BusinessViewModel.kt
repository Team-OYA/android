package com.thepop.android.ui.business

import androidx.lifecycle.ViewModel
import com.thepop.android.data.service.CommunityService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BusinessViewModel @Inject constructor(
    private val communityService: CommunityService
) : ViewModel() {

}