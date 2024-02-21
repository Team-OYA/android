package com.thepop.android.ui.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thepop.android.R
import com.thepop.android.databinding.ActivityWriteBinding
import com.thepop.android.util.DialogUtil

class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goBack()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}