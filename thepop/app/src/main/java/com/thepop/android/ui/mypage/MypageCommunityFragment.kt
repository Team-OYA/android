package com.thepop.android.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.thepop.android.R
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.databinding.FragmentMypageCommunityBinding
import com.thepop.android.ui.common.CommunityListAdapter
import com.thepop.android.ui.community.CommunityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MypageCommunityFragment : Fragment() {

    private lateinit var binding: FragmentMypageCommunityBinding
    private val communityViewModel by viewModels<CommunityViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageCommunityBinding.inflate(inflater, container, false)
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
        communityViewModel.communityPostList.observe(viewLifecycleOwner) {
            setCommunityList(it)
        }
    }

    private fun getMypagePopup() {
        communityViewModel.getCommunityPostList("collections", 0, 10)
    }

    private fun setCommunityList(data: CommunityListResponse.CommunityDetailResponseList) {
        val recyclerViewList = binding.rvMypageCommunity
        val adapter = CommunityListAdapter(communityViewModel, data)
        recyclerViewList.adapter = adapter
    }

}