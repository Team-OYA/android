package com.thepop.android.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepop.android.data.model.popup.PopupDetailResponse
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.data.service.PopupService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val popupService: PopupService
) : ViewModel() {

    private val _popupRecommendList: MutableLiveData<PopupListResponse.PopupList> = MutableLiveData()
    val popupRecommendList: LiveData<PopupListResponse.PopupList> = _popupRecommendList
    fun setPopupRecommendList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = popupService.getPopupRecommend(0, 5)
                _popupRecommendList.postValue(response.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val _popupList: MutableLiveData<PopupListResponse.PopupList> = MutableLiveData()
    val popupList: LiveData<PopupListResponse.PopupList> = _popupList

    fun setPopupList(type: String, pageNo: Int, amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = popupService.getPopupList(type, pageNo, amount)
                _popupList.postValue(response.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setPaginationPopupList(type: String, pageNo: Int, amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = popupService.getPopupList(type, pageNo, amount)
                if (response.data.popups.isEmpty()) {
                    Log.e("setPaginationPopupList", "데이터가 없습니다.")
                } else {
                    val currentList = _popupList.value?.popups?.toMutableList() ?: mutableListOf()
                    currentList.addAll(response.data.popups)
                    _popupList.postValue(PopupListResponse.PopupList(currentList))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val _popupDetail: MutableLiveData<PopupDetailResponse.PopupDetailData> = MutableLiveData()
    val popupDetail: LiveData<PopupDetailResponse.PopupDetailData> = _popupDetail

    fun setPopupDetail(popupId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = popupService.getPopupDetail(popupId)
                _popupDetail.postValue(response.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}