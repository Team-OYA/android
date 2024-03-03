package com.thepop.android.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.data.service.CommunityService
import com.thepop.android.data.service.PopupService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val communityService: CommunityService,
    private val popupService: PopupService
) : ViewModel() {

    private val _communityPostList: MutableLiveData<CommunityListResponse.CommunityDetailResponseList> = MutableLiveData()
    val communityPostList: LiveData<CommunityListResponse.CommunityDetailResponseList> = _communityPostList

    fun getCollectionCommunityPostList(type: String, pageNo: Int, amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = communityService.getCommunityList(type, pageNo, amount)
                _communityPostList.postValue(response.data)
            } catch (e: Exception) {
                Log.e("getCommunityPostList", e.toString())
            }
        }
    }

    private val _popupList: MutableLiveData<PopupListResponse.PopupList> = MutableLiveData()
    val popupList: LiveData<PopupListResponse.PopupList> = _popupList

    fun getCollectionPopupList(type: String, pageNo: Int, amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = popupService.getPopupList(type, pageNo, amount)
                _popupList.postValue(response.data)
            } catch (e: Exception) {
                Log.e("getCollectionPopupList", e.toString())
            }
        }
    }
}