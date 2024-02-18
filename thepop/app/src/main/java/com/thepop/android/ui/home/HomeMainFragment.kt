package com.thepop.android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thepop.android.R
import com.thepop.android.databinding.FragmentHomeMainBinding

class HomeMainFragment : Fragment() {

    private lateinit var binding: com.thepop.android.databinding.FragmentHomeMainBinding
    private val adImageList = arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blurImage()
        setupViewPager()
    }

    private fun blurImage() {
        val blurImageView = binding.ivBlur
        blurImageView.setBlur(9)
    }

    private fun setupViewPager() {
        val viewPager = binding.vpAdImages
        setAdImageList()
        val adImageAdapter = HomeMainPagerAdapter(adImageList)
        viewPager.adapter = adImageAdapter
        viewPager.offscreenPageLimit = 3

    }

    private fun setAdImageList() {
        adImageList.add(R.drawable.img_ad_test)
        adImageList.add(R.drawable.img_ad_test)
        adImageList.add(R.drawable.img_ad_test)
    }
}