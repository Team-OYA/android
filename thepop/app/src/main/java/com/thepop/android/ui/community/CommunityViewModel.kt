package com.thepop.android.ui.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.data.service.CommunityService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val communityService: CommunityService
) : ViewModel() {

    private val _communityPostList: MutableLiveData<CommunityListResponse.CommunityDetailResponseList> = MutableLiveData()
    val communityPostList: LiveData<CommunityListResponse.CommunityDetailResponseList> = _communityPostList

    fun getCommunityPostList(type: String, pageNo: Int, amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = communityService.getCommunityList(type, pageNo, amount)
                _communityPostList.postValue(response.data)
            } catch (e: Exception) {
                Log.e("getCommunityPostList", e.toString())
            }
        }
    }


    fun checkVote(voteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                communityService.checkVote(voteId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun cancelVote(voteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                communityService.cancelVote(voteId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}