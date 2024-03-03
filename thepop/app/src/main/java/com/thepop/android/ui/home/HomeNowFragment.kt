package com.thepop.android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.databinding.FragmentHomeNowBinding
import com.thepop.android.ui.common.PopupAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeNowFragment : Fragment() {

    private lateinit var binding: FragmentHomeNowBinding
    private val type = "progress"
    private val homeViewModel by viewModels<HomeViewModel>()
    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        currentPage = 0
        getPopupNowList()
        dataObserver()
        scrollPopupNowList()
    }

    private fun getPopupNowList() {
        homeViewModel.setPopupList(type, currentPage, 5)
    }

    private fun dataObserver() {
        homeViewModel.popupList.observe(viewLifecycleOwner) {
            setPopupNowList(it)
        }
    }

    private fun setPopupNowList(popupList: PopupListResponse.PopupList) {
        val adapter = PopupAdapter(popupList)
        binding.rvHomeNow.adapter = adapter
    }

    private fun scrollPopupNowList() {
        binding.rvHomeNow.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    currentPage += 1
                    homeViewModel.setPaginationPopupList(type, currentPage, 5)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentPage = 0
    }

    override fun onResume() {
        super.onResume()
        init()
    }

}