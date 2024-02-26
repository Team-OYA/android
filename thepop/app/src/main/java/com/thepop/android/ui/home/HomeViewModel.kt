package com.thepop.android.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.data.service.PopupService
import com.thepop.android.domain.repository.PopupRepository
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

    private val _popupNowList: MutableLiveData<PopupListResponse.PopupList> = MutableLiveData()
    val popupNowList: LiveData<PopupListResponse.PopupList> = _popupNowList

    fun setPopupNowList(pageNo: Int, amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = popupService.getPopupList("progress", pageNo, amount)
                _popupNowList.postValue(response.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private val _popupScheduledList: MutableLiveData<PopupListResponse.PopupList> = MutableLiveData()
    val popupScheduledList: LiveData<PopupListResponse.PopupList> = _popupScheduledList

    fun setPopupScheduledList(pageNo: Int, amount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = popupService.getPopupList("scheduled", pageNo, amount)
                _popupScheduledList.postValue(response.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}