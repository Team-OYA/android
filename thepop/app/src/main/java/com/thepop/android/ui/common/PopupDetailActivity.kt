package com.thepop.android.ui.common

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.lifecycle.lifecycleScope
import com.mukesh.MarkDown
import com.thepop.android.R
import com.thepop.android.data.service.PopupService
import com.thepop.android.databinding.ActivityPopupDetailBinding
import com.thepop.android.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PopupDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPopupDetailBinding
    @Inject lateinit var popupService: PopupService
    private val viewModel: HomeViewModel by viewModels()
    private var popupId: Int = 0
    private var isScraped: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPopupDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        popupId = intent.getIntExtra("popupId", 1)

        getPopupDetail()
        init()
    }

    private fun setPopupDetail(popupId: Int) {
        viewModel.setPopupDetail(popupId)
    }

    private fun getPopupDetail() {
        viewModel.popupDetail.observe(this) {
            lifecycleScope.launch {
                binding.tvPopupTitle.text = it.title
                binding.tvPopupContent.setContent {
                    MaterialTheme {
                        MarkDown(
                            text = it.description,
                        )
                    }
                }
                binding.tvPopupDate.text = it.pulledDate
                isScraped = it.collected
                if (isScraped) {
                    binding.btnBookmark.setImageResource(R.drawable.vi_bookmark_true)
                    isScraped = false
                } else {
                    binding.btnBookmark.setImageResource(R.drawable.vi_bookmark_false)
                    isScraped = true
                }
            }
            Log.e("getPopupDetail", it.toString())
        }
    }


    private fun init() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        setPopupDetail(popupId)
        setScrapButton()
    }

    private fun scrapPopup(popupId: Int) {
        viewModel.scrapPopup(popupId)
    }
    private fun setScrapButton() {
        if (isScraped) {
            binding.btnBookmark.setImageResource(R.drawable.vi_bookmark_true)
        }
        binding.btnBookmark.setOnClickListener {
            if (isScraped) {
                binding.btnBookmark.setImageResource(R.drawable.vi_bookmark_true)
                isScraped = false
            } else {
                binding.btnBookmark.setImageResource(R.drawable.vi_bookmark_false)
                isScraped = true
            }
            scrapPopup(popupId)
        }
    }


    override fun onStop() {
        finish()
        super.onStop()
    }

}
