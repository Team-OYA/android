package com.thepop.android.ui.community

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thepop.android.R
import com.thepop.android.data.model.community.CategoryResponse
import com.thepop.android.data.model.community.CommunityWriteRequest
import com.thepop.android.data.model.community.CommunityWriteVoteRequest
import com.thepop.android.data.service.CommunityService
import com.thepop.android.databinding.ActivityWriteBinding
import com.thepop.android.domain.entity.community.CommunityVoteWriteData
import com.thepop.android.domain.entity.community.CommunityWriteData
import com.thepop.android.util.ImageUtil
import com.thepop.android.util.PermissionUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject


@AndroidEntryPoint
class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding
    private var addImageList: ArrayList<Uri> = ArrayList()
    private var boardImages: List<MultipartBody.Part> = emptyList()
    @Inject lateinit var communityService: CommunityService
    private var writeVoteData: CommunityVoteWriteData? = null
    private var writeData: CommunityWriteData? = null
    private lateinit var categoryList: List<CategoryResponse.CategoryList.CategoryData>
    private var categoryCode: String = "CG000001"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setImagePlus()
        goBack()
        setAddImageAdapter()
        setCompleteButton()
        getCategoryList()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setCompleteButton() {
        binding.btnComplete.setOnClickListener {
            if (binding.editTitle.text.toString().isNotBlank() && binding.editContent.text.toString().isNotBlank()) {
                if (binding.etPostVote1.text.isNotBlank() && binding.etPostVote2.text.isNotBlank()) {
                    writeVoteData = CommunityVoteWriteData(
                        title = binding.editTitle.text.toString(),
                        description = binding.editContent.text.toString(),
                        categoryCode = categoryCode,
                        votes = listOf(
                            binding.etPostVote1.text.toString(),
                            binding.etPostVote2.text.toString()
                        )
                    )

                    Log.e("WriteVoteData", writeVoteData.toString())

                    val postDTO = CommunityWriteVoteRequest(
                            title = writeVoteData!!.title,
                            description = writeVoteData!!.description,
                            categoryCode = writeVoteData!!.categoryCode,
                            votes = writeVoteData!!.votes
                        )

                    writeVotePost(postDTO)

                } else if (binding.etPostVote1.text.isNotBlank() || binding.etPostVote2.text.isNotBlank()) {
                    Toast.makeText(this, "투표 항목을 모두 입력해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    writeData = CommunityWriteData(
                        title = binding.editTitle.text.toString(),
                        description = binding.editContent.text.toString(),
                        categoryCode = categoryCode
                    )

                    val postDTO = CommunityWriteRequest(
                            title = writeData!!.title,
                            description = writeData!!.description,
                            categoryCode = writeData!!.categoryCode
                    )


                    writePost(postDTO)
                }
            } else {
                Toast.makeText(this, "제목과 내용을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
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
                            val boardImage = ImageUtil.createImagePartFromUri(this@WriteActivity, imageUri)
                            boardImage?.let {
                                boardImages = boardImages.toMutableList().apply {
                                    add(it)
                                }
                            }
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

    private fun writePost(data: CommunityWriteRequest) {
        lifecycleScope.launch {
            try {
                Log.e("WriteActivity", boardImages.toString())
                val response = communityService.createCommunityPost("basic", data, boardImages)
                Log.e("WriteActivity", response.toString())
                Toast.makeText(this@WriteActivity, "글이 작성되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {
                Log.e("WriteActivity", e.toString())
            }
        }
    }

    private fun writeVotePost(data: CommunityWriteVoteRequest) {
        lifecycleScope.launch {
            try {
                val response = communityService.createCommunityPostVote("vote", data, boardImages)
                Toast.makeText(this@WriteActivity, "글이 작성되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {
                Log.e("WriteActivity", e.toString())
            }
        }
    }

    private fun getCategoryList() {
        lifecycleScope.launch {
            try {
                val response = communityService.getCategoryList()
                if (response.data.categories[0].description != "전체 보기") {
                    categoryList = response.data.categories.drop(1)
                } else {
                    categoryList = response.data.categories
                }

                setCategorySpinner()
            } catch (e: Exception) {
                Log.e("WriteActivity", e.toString())
            }
        }
    }

    private fun setCategorySpinner() {
        val categoryAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, categoryList.map { it.description }
        )
        binding.spinnerCategory.adapter = categoryAdapter
        binding.spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                categoryCode = categoryList[position].code
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

}
