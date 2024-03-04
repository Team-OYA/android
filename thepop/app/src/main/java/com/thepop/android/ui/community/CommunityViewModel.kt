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

    private val _communityAdditionPostList: MutableLiveData<CommunityListResponse.CommunityDetailResponseList> = MutableLiveData()
    val communityAdditionPostList: LiveData<CommunityListResponse.CommunityDetailResponseList> = _communityAdditionPostList

    fun getPaginationCommunityPostList(type: String, pageNo: Int, amount: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = communityService.getCommunityList(type, pageNo, amount)
                _communityAdditionPostList.postValue(response.data)
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
                communityService.deleteCommunityPost(postId)
                // 현재 게시물 목록에서 postId와 일치하지 않는 게시물만 필터링하여 새로운 목록을 생성합니다.
                val updatedList = _communityPostList.value?.communityDetailResponseList?.filter { it.communityId != postId } ?: listOf()
                // 새로운 CommunityDetailResponseList 객체를 생성하여 업데이트된 목록을 포함시킵니다.
                val updatedResponseList = CommunityListResponse.CommunityDetailResponseList(updatedList)
                // LiveData를 새로운 값으로 업데이트합니다.
                _communityPostList.postValue(updatedResponseList)
                Log.e("deleteCommunityPost", "Post deleted successfully.")
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