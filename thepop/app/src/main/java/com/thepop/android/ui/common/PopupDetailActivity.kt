package com.thepop.android.ui.common

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.thepop.android.data.service.PopupService
import com.thepop.android.databinding.ActivityPopupDetailBinding
import com.thepop.android.ui.community.CommunityViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPopupDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        popupId = intent.getIntExtra("popupId", 1)

        init()
        getPopupDetail()
    }

    private fun setPopupDetail(popupId: Int) {
        viewModel.setPopupDetail(popupId)
    }

    private fun getPopupDetail() {
        viewModel.popupDetail.observe(this) {
            lifecycleScope.launch {
                binding.tvPopupTitle.text = it.title
                binding.tvPopupContent.text = it.description
                binding.tvPopupDate.text = it.pulledDate
            }
        }
    }


    private fun init() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        setPopupDetail(popupId)
    }

}
