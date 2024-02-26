package com.thepop.android.ui.common

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.thepop.android.R
import com.thepop.android.data.service.CommunityService
import com.thepop.android.databinding.ActivityCommunityDetailBinding
import com.thepop.android.util.DialogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class CommunityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityDetailBinding
    @Inject lateinit var communityService: CommunityService
    private var postId: Int = 0
    private var isScraped: Boolean = false
    private var postImages: List<String> = listOf()

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postId = intent.getIntExtra("postId", 1)

        goBack()
        setDialog()
        getCommunityDetail(postId)
        setScrapButton()
    }

    private fun setScrapButton() {
        if (isScraped) {
            binding.btnStar.setImageResource(R.drawable.vi_star_true)
        }
        binding.btnStar.setOnClickListener {
            if (isScraped) {
                binding.btnStar.setImageResource(R.drawable.vi_star_false)
                isScraped = false
            } else {
                binding.btnStar.setImageResource(R.drawable.vi_star_true)
                isScraped = true
            }
            scrapCommunityPost(postId)
        }
    }
    private fun setDialog() {
        val dialog = DialogUtil(this)
        binding.btnMore.setOnClickListener {
            dialog.show {
                if (it) {
                    deleteCommunityPost(postId)
                    finish()
                } else {
                    Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goBack() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun getCommunityDetail(postId: Int) {
        lifecycleScope.launch {
            try {
                val response = communityService.getCommunityDetail(postId)
                val postData = response.data
                binding.tvPostTitle.text = postData.title
                binding.tvPostContent.text = postData.description
                binding.tvPostInfoUserName.text = postData.nickname
                isScraped = postData.collected
                postImages = postData.imageList
                setupViewPager()
                if (postData.userType != "BUSINESS") {
                    binding.ivPostBadge.visibility = android.view.View.GONE
                }
                if (postData.collected) {
                    binding.btnStar.setImageResource(R.drawable.vi_star_true)
                }
                if (postData.written) {
                    binding.btnMore.visibility = android.view.View.VISIBLE
                }
                if (postData.voteResponseList.size >= 2) {
                    binding.clPostVote.visibility = android.view.View.VISIBLE
                    binding.tvPostVote1.text = postData.voteResponseList[0].content
                    binding.tvPostVote2.text = postData.voteResponseList[1].content
                    if (postData.voteResponseList[0].checked) {
                        binding.clPostVote1.background = getDrawable(R.drawable.bg_vote_true)
                        binding.tvPostVotePercent1.visibility = android.view.View.VISIBLE
                        binding.tvPostVotePercent2.visibility = android.view.View.VISIBLE
                    }
                    if (postData.voteResponseList[1].checked) {
                        binding.clPostVote2.background = getDrawable(R.drawable.bg_vote_true)
                        binding.tvPostVotePercent1.visibility = android.view.View.VISIBLE
                        binding.tvPostVotePercent2.visibility = android.view.View.VISIBLE
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteCommunityPost(postId: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                communityService.deleteCommunityPost(postId)
            } catch (e: Exception) {
                Toast.makeText(this@CommunityDetailActivity, "삭제 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun scrapCommunityPost(postId: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                communityService.scrapCommunityPost(postId)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setupViewPager() {
        try {
            val viewPager = CommunityDetailIVpAdapter(postImages)
            binding.vpPostImages.adapter = viewPager
        } catch (e: Exception) {
            Log.e("CommunityDetailActivity", e.toString())
        }
    }

}
