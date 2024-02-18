package com.thepop.android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.jgabrielfreitas.core.BlurImageView
import com.thepop.android.R
import com.thepop.android.databinding.FragmentHomeMainBinding

class HomeMainFragment : Fragment() {

    private lateinit var binding: FragmentHomeMainBinding
    private val adImageList = arrayListOf<Int>()
    private lateinit var blurImageView: BlurImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blurImageView = binding.ivBlur
        setupViewPager()
        blurImage()
    }

    private fun setupViewPager() {
        val viewPager = binding.vpAdImages
        setAdImageList()
        val adImageAdapter = HomeMainPagerAdapter(adImageList)
        viewPager.adapter = adImageAdapter
        viewPager.offscreenPageLimit = 3

        // ViewPager 페이지 변경 리스너 추가
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                val imageResId = adImageList[position]
//                updateImage(imageResId)
//            }
//
//        })
    }

//    private fun updateImage(imageResId: Int) {
//        Glide.with(this@HomeMainFragment)
//            .load(imageResId)
//            .into(blurImageView)
//    }

    private fun blurImage() {
        blurImageView.setBlur(9)
    }

    private fun setAdImageList() {
        adImageList.add(R.drawable.img_ad_test)
        adImageList.add(R.drawable.img_ad_test2)
        adImageList.add(R.drawable.img_ad_test3)
    }
}