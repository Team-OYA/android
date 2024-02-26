package com.thepop.android.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.thepop.android.data.service.PopupService
import com.thepop.android.databinding.ActivityPopupDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PopupDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPopupDetailBinding
    @Inject lateinit var popupService: PopupService

    private var popupId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPopupDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        popupId = intent.getIntExtra("popupId", 1)

        init()
        getPopupDetail(popupId)
    }

    private fun getPopupDetail(popupId: Int) {
        lifecycleScope.launch {
            try {
                val response = popupService.getPopupDetail(popupId)
                binding.tvPopupTitle.text = response.data.title
                binding.tvPopupContent.text = response.data.description
                binding.tvPopupDate.text = response.data.pulledDate
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun init() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

}
