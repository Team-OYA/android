package com.thepop.android.ui.home

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.thepop.android.R
import com.thepop.android.databinding.FragmentHomeBinding
import com.thepop.android.util.BackgroundChangeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), BackgroundChangeListener {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabs()
    }

    private fun setTabs() {
        binding.vpHome.adapter = HomePagerAdapter(requireActivity())
        binding.vpHome.isUserInputEnabled = false
        TabLayoutMediator(binding.tlHome, binding.vpHome) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.home_tab1)
                1 -> getString(R.string.home_tab2)
                else -> getString(R.string.home_tab3)
            }
        }.attach()

        // 탭이 선택될 때마다 이미지 업데이트
        binding.tlHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    updateImages(it.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // 초기 이미지 설정
        updateImages(0)
    }

    private fun updateImages(position: Int) {
        when (position) {
            0 -> {
                binding.ivLogo.setImageResource(R.drawable.img_logo)
                binding.ivSearch.setImageResource(R.drawable.vi_search_white)
                binding.tlHome.tabTextColors = resources.getColorStateList(R.drawable.color_tab_home_main)
                binding.tlHome.setSelectedTabIndicatorColor(resources.getColor(R.color.white))
                binding.fixed.background = resources.getDrawable(R.drawable.bg_toolbar_top)
                binding.nsvHome.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
                    // 스크롤 위치에 따른 조건 확인
                    if (scrollY > 0) {
                        binding.fixed.background = resources.getDrawable(R.drawable.bg_toolbar)
                    } else {
                        binding.fixed.background = resources.getDrawable(R.drawable.bg_toolbar_top)
                    }
                }
            }

            else -> {
                binding.ivLogo.setImageResource(R.drawable.img_logo_color)
                binding.ivSearch.setImageResource(R.drawable.vi_search)
                binding.tlHome.tabTextColors = resources.getColorStateList(R.drawable.color_tab_home_other)
                binding.tlHome.setSelectedTabIndicatorColor(resources.getColor(R.color.black))
                binding.fixed.setBackgroundColor(resources.getColor(R.color.white))
            }
        }
    }

    override fun changeBackground() {
        binding.fixed.setBackgroundColor(resources.getColor(R.color.white))
    }
}
