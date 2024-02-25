package com.thepop.android.ui.community

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thepop.android.R
import com.thepop.android.databinding.ActivityWriteBinding
import com.thepop.android.util.ImageUtil
import com.thepop.android.util.PermissionUtil
import kotlinx.coroutines.launch
import okhttp3.MultipartBody


class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding
    private var addImageList: ArrayList<Uri> = ArrayList()
    private var boardImage: MultipartBody.Part? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setImagePlus()
        goBack()
        setAddImageAdapter()
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
                    PermissionUtil.openGallery(this, setImageResult)
                } else {
                    PermissionUtil.requestGalleryPermission(this)
                }
            }
        }
    }

    private val setImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                data?.data?.let { imageUri ->
                    addImageList.add(imageUri)

                    lifecycleScope.launch {
                        try {
                            boardImage = ImageUtil.createImagePartFromUri(this@WriteActivity, imageUri)!!
                        } catch (e: Exception) {
                            Log.e("YourActivity", "Error during image conversion", e)
                        }
                    }
                    binding.rvPhoto.adapter?.notifyItemInserted(addImageList.size - 1)

                }
            }
        }

    private fun setAddImageAdapter() {
        val recyclerViewAddImage: RecyclerView = findViewById(R.id.rv_photo)
        val addImageAdapter = AddImageAdapter(addImageList)
        val layoutManager = GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false)
        recyclerViewAddImage.layoutManager = layoutManager
        recyclerViewAddImage.adapter = addImageAdapter
    }

}