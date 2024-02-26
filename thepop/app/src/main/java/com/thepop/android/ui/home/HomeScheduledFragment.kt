package com.thepop.android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.databinding.FragmentHomeScheduledBinding
import com.thepop.android.ui.common.PopupAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScheduledFragment : Fragment() {

    private lateinit var binding: FragmentHomeScheduledBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScheduledBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPopupScheduledList()
        dataObserver()
    }
    private fun setPopupScheduledList() {
        homeViewModel.setPopupNowList(currentPage, 5)
    }

    private fun dataObserver() {
        homeViewModel.popupScheduledList.observe(viewLifecycleOwner) {
            setPopupScheduledList(it)
        }
    }

    private fun setPopupScheduledList(popupList: PopupListResponse.PopupList) {
        val adapter = PopupAdapter(popupList)
        binding.rvHomeScheduled.adapter = adapter
    }
}