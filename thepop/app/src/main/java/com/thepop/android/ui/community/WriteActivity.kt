package com.thepop.android.ui.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thepop.android.databinding.ActivityWriteBinding
import com.thepop.android.util.PermissionUtil


class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setImagePlus()
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

    //갤러리 열기
    private fun setImagePlus() {

        binding.ivPhoto.setOnClickListener {
            PermissionUtil.checkGalleryPermission(this).let {
                if (it) {
                    PermissionUtil.openGallery(this)
                } else {
                    PermissionUtil.requestGalleryPermission(this)
                }
            }
        }
    }
}