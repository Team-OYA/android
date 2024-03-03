package com.thepop.android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.databinding.FragmentHomeScheduledBinding
import com.thepop.android.ui.common.PopupAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScheduledFragment : Fragment() {

    private lateinit var binding: FragmentHomeScheduledBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private var currentPage = 0
    private val type = "scheduled"


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
        scrollPopupScheduledList()
    }
    private fun setPopupScheduledList() {
        homeViewModel.setPopupList(type, currentPage, 5)
    }

    private fun dataObserver() {
        homeViewModel.popupList.observe(viewLifecycleOwner) {
            setPopupScheduledList(it)
        }
    }

    private fun setPopupScheduledList(popupList: PopupListResponse.PopupList) {
        val adapter = PopupAdapter(popupList)
        binding.rvHomeScheduled.adapter = adapter
    }

    private fun scrollPopupScheduledList() {
        binding.rvHomeScheduled.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    currentPage += 1
                    homeViewModel.setPaginationPopupList(type, currentPage, 5)
                }
            }
        })
    }
}