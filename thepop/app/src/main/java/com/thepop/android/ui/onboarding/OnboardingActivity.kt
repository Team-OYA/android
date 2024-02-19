package com.thepop.android.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.thepop.android.MainActivity
import com.thepop.android.R
import com.thepop.android.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
        addIndicatorDots(onboardingList.size)
        setOnNextButton()
    }

    private fun initViewPager() {
        val viewPager = binding.onboardingViewpager
        val onBoardingAdapter = OnboardingAdapter(onboardingList)
        viewPager.adapter = onBoardingAdapter
        viewPager.registerOnPageChangeCallback(onPageChangeCallback)
    }

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            updateIndicator(position)
        }
    }

    private fun addIndicatorDots(pageCount: Int) {
        for (i in 0 until pageCount) {
            val dot = ImageView(this).apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        this@OnboardingActivity,
                        R.drawable.ind_onboarding_false
                    )
                )
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(8, 0, 8, 0)
                }
            }
            binding.onBoardingIndicator.addView(dot)
        }
    }

    private fun updateIndicator(position: Int) {
        for (i in 0 until binding.onBoardingIndicator.childCount) {
            val dot = binding.onBoardingIndicator.getChildAt(i) as ImageView
            dot.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    if (i == position) R.drawable.ind_onboarding_true else R.drawable.ind_onboarding_false
                )
            )
        }
    }

    private fun setOnNextButton() {
        val onBoardingButton = binding.nextButton
        onBoardingButton.setOnClickListener {
            if (binding.onboardingViewpager.currentItem < onboardingList.size - 1) {
                binding.onboardingViewpager.currentItem += 1
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}