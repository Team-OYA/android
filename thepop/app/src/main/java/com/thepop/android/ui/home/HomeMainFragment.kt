package com.thepop.android.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.thepop.android.R
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.databinding.FragmentHomeMainBinding
import com.thepop.android.ui.common.PopupRecommendAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMainFragment : Fragment() {

    private lateinit var binding: FragmentHomeMainBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private val adImageList = arrayListOf<Int>()
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private var currentState = 0
    private lateinit var viewPager: ViewPager2
    private var timer: Long = 10000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = homeViewModel

        viewPager = binding.vpAdImages
        setupViewPager()
        startAutoScroll()
        getPopupRecommendList()
        dataObserver()
    }

    private fun setupViewPager() {
        setAdImageList()
        val adImageAdapter = HomeMainPagerAdapter(adImageList)
        viewPager.adapter = adImageAdapter
        viewPager.offscreenPageLimit = 3

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
                timer = 0 // 페이지가 전환되면 타이머를 0으로 설정
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                currentState = state
                }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if(currentState == ViewPager2.SCROLL_STATE_DRAGGING && currentPage == position) {
                    if(currentPage == 0) viewPager.currentItem = 2
                    else if(currentPage == 2) viewPager.currentItem = 0
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    private fun setAdImageList() {
        adImageList.add(R.drawable.img_ad_test)
        adImageList.add(R.drawable.img_ad_test2)
        adImageList.add(R.drawable.img_ad_test3)
    }

    private fun startAutoScroll() {
        handler.postDelayed(scrollRunnable, timer)
    }

    private val scrollRunnable = object : Runnable {
        override fun run() {
            if (currentPage == adImageList.size - 1) {
                viewPager.setCurrentItem(0, true)
            } else {
                viewPager.setCurrentItem(currentPage + 1, true)
            }
            handler.postDelayed(this, 10000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(scrollRunnable)
    }
    
    private fun getPopupRecommendList() {
        homeViewModel.setPopupRecommendList()
    }

    private fun dataObserver() {
        homeViewModel.popupRecommendList.observe(viewLifecycleOwner) {
            setPopupRecommendList(it)
        }
    }

    private fun setPopupRecommendList(popupList: PopupListResponse.PopupList) {
        val adapter = PopupRecommendAdapter(popupList)
        binding.rvHomeRecommend.layoutManager = GridLayoutManager(
            requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
        binding.rvHomeRecommend.adapter = adapter
    }
}

