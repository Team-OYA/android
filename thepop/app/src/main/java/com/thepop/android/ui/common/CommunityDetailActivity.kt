package com.thepop.android.ui.common

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.thepop.android.R
import com.thepop.android.data.service.CommunityService
import com.thepop.android.databinding.ActivityCommunityDetailBinding
import com.thepop.android.ui.community.CommunityViewModel
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
    private var isVoteFirst: Boolean = false
    private var isVoteSecond: Boolean = false
    private var voteSum1: Int = 0
    private var voteSum2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
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
                    isVoteFirst = postData.voteResponseList[0].checked
                    isVoteSecond = postData.voteResponseList[1].checked
                    voteSum1 = postData.voteResponseList[0].voteSum
                    voteSum2 = postData.voteResponseList[1].voteSum
                    if (isVoteFirst or isVoteSecond) {
                        setVotePercent(voteSum1, voteSum2)
                    }
                    if (isVoteFirst) {
                        binding.clPostVote1.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                    }
                    if (isVoteSecond) {
                        binding.clPostVote2.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                    }

                    binding.clPostVote2.setOnClickListener {
                        if (!postData.voteResponseList[1].checked) {
                            setVoteSystem(postData.voteResponseList[1].vote_id, false)
                            setVoteSystem(postData.voteResponseList[0].vote_id, true)
                        }
                    }

                    binding.clPostVote1.setOnClickListener {
                        if (!isVoteFirst) {
                            setVoteSystem(postData.voteResponseList[0].vote_id , false)
                            setVoteSystem(postData.voteResponseList[1].vote_id, true)
                            isVoteFirst = true
                            isVoteSecond = false
                            voteSum1++
                            voteSum2--
                            binding.clPostVote1.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                            binding.clPostVote2.background = binding.root.context.getDrawable(R.drawable.bg_vote)
                            setVotePercent(voteSum1, voteSum2)
                        }
                    }

                    binding.clPostVote2.setOnClickListener {
                        if (!isVoteSecond) {
                            setVoteSystem(postData.voteResponseList[1].vote_id, false)
                            setVoteSystem(postData.voteResponseList[0].vote_id, true)
                            isVoteFirst = false
                            isVoteSecond = true
                            voteSum1--
                            voteSum2++
                            binding.clPostVote1.background = binding.root.context.getDrawable(R.drawable.bg_vote)
                            binding.clPostVote2.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                            setVotePercent(voteSum1, voteSum2)
                        }
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

    private fun setVoteSystem(voteId: Int, isVoted: Boolean) {
        lifecycleScope.launch {
            if (isVoted) {
                communityService.cancelVote(voteId)
            } else {
                communityService.checkVote(voteId)
            }
        }
    }

    private fun setVotePercent(voteSum1: Int, voteSum2: Int) {
        val votePercent1 = (voteSum1 / (voteSum1 + voteSum2).toFloat() * 100).toInt()
        val votePercent2 = (voteSum2 / (voteSum1 + voteSum2).toFloat() * 100).toInt()
        binding.tvPostVotePercent1.visibility = android.view.View.VISIBLE
        binding.tvPostVotePercent2.visibility = android.view.View.VISIBLE
        binding.tvPostVotePercent1.text = "${votePercent1}%"
        binding.tvPostVotePercent2.text = "${votePercent2}%"
    }
}
