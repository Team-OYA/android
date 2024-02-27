package com.thepop.android.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.thepop.android.R
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.databinding.FragmentMypagePopupBinding
import com.thepop.android.ui.common.CommunityListAdapter
import com.thepop.android.ui.common.PopupAdapter
import com.thepop.android.ui.community.CommunityViewModel
import com.thepop.android.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MypagePopupFragment : Fragment() {

    private lateinit var binding: FragmentMypagePopupBinding
    private val mypageViewModel by viewModels<MyPageViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypagePopupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        getMypagePopup()
        dataObserver()
    }

    private fun dataObserver() {
        mypageViewModel.popupList.observe(viewLifecycleOwner) {
            setPopupList(it)
        }
    }

    private fun getMypagePopup() {
        mypageViewModel.getCollectionPopupList("collections", 0, 10)
    }

    private fun setPopupList(data: PopupListResponse.PopupList) {
        val recyclerViewList = binding.rvMypagePopup
        val adapter = PopupAdapter(data)
        recyclerViewList.adapter = adapter
    }

}