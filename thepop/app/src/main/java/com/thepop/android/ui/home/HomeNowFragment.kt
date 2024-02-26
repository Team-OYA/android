package com.thepop.android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.databinding.FragmentHomeNowBinding
import com.thepop.android.ui.common.PopupAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeNowFragment : Fragment() {

    private lateinit var binding: FragmentHomeNowBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private var currentPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPopupNowList()
        dataObserver()
    }

    private fun getPopupNowList() {
        homeViewModel.setPopupNowList(currentPage, 5)
    }

    private fun dataObserver() {
        homeViewModel.popupNowList.observe(viewLifecycleOwner) {
            setPopupNowList(it)
        }
    }

    private fun setPopupNowList(popupList: PopupListResponse.PopupList) {
        val adapter = PopupAdapter(popupList)
        binding.rvHomeNow.adapter = adapter
    }

}