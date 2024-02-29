package com.thepop.android.ui.business

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.thepop.android.R
import com.thepop.android.databinding.ActivityBusinessBinding

class BusinessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusinessBinding
    private val viewModel: BusinessViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        goBack()
    }

    private fun goBack() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}