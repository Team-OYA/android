package com.thepop.android.ui.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepop.android.data.model.community.CommunityDetailResponse
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

    fun getPaginationCommunityPostList(type: String, pageNo: Int, amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = communityService.getCommunityList(type, pageNo, amount)
                if (response.data.communityDetailResponseList.isEmpty()) {
                    Log.e("getPaginationCommunityPostList", "데이터가 없습니다.")
                } else {
                    val currentList =
                        _communityPostList.value?.communityDetailResponseList?.toMutableList()
                        ?: mutableListOf()
                    currentList.addAll(response.data.communityDetailResponseList)
                    _communityPostList.postValue(CommunityListResponse.CommunityDetailResponseList(currentList))
                }
            } catch (e: Exception) {
                Log.e("getPaginationCommunityPostList", e.toString())
            }
        }
    }




    fun checkVote(voteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                communityService.checkVote(voteId)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("checkVote", e.toString())
            }
        }
    }

    fun cancelVote(voteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                communityService.cancelVote(voteId)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("cancelVote", e.toString())
            }
        }
    }

    private val _communityDetail: MutableLiveData<CommunityDetailResponse.CommunityDetailData> = MutableLiveData()
    val communityDetail: LiveData<CommunityDetailResponse.CommunityDetailData> = _communityDetail

    fun getCommunityDetail(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = communityService.getCommunityDetail(postId)
                _communityDetail.postValue(response.data)
            } catch (e: Exception) {
                Log.e("getCommunityDetail", e.toString())
            }
        }
    }

    fun deleteCommunityPost(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = communityService.deleteCommunityPost(postId)
                Log.e("deleteCommunityPost", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("deleteCommunityPost", e.toString())
            }
        }
    }

    fun scrapCommunityPost(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = communityService.scrapCommunityPost(postId)
                Log.e("scrapCommunityPost", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("scrapCommunityPost", e.toString())
            }
        }
    }

    fun getCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = communityService.getCategoryList()
                Log.e("getCategoryList", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("getCategoryList", e.toString())
            }
        }
    }

}