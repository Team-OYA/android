package com.thepop.android.ui.common

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.thepop.android.R
import com.thepop.android.databinding.ActivityCommunityDetailBinding
import com.thepop.android.ui.community.CommunityViewModel
import com.thepop.android.util.DialogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommunityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityDetailBinding
    private val viewModel: CommunityViewModel by viewModels()
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
        setCommunityDetail()
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
        viewModel.getCommunityDetail(postId)
    }

    private fun setCommunityDetail() {
        viewModel.communityDetail.observe(this) { response ->
            binding.tvPostTitle.text = response.title
            binding.tvPostContent.text = response.description
            binding.tvPostInfoUserName.text = response.nickname
            isScraped = response.collected
            postImages = response.imageList
            setupViewPager()
            if (response.userType != "BUSINESS") {
                binding.ivPostBadge.visibility = android.view.View.GONE
            }
            if (response.collected) {
                binding.btnStar.setImageResource(R.drawable.vi_star_true)
            }
            if (response.written) {
                binding.btnMore.visibility = android.view.View.VISIBLE
            }
            if ((response.voteResponseList.size ?: 0) >= 2) {
                binding.clPostVote.visibility = android.view.View.VISIBLE
                binding.tvPostVote1.text = response.voteResponseList[0].content
                binding.tvPostVote2.text = response.voteResponseList[1].content
                isVoteFirst = response.voteResponseList[0].checked
                isVoteSecond = response.voteResponseList[1].checked
                voteSum1 = response.voteResponseList[0].voteSum
                voteSum2 = response.voteResponseList[1].voteSum
                if (isVoteFirst or isVoteSecond) {
                    setVotePercent(voteSum1, voteSum2)
                }
                if (isVoteFirst) {
                    binding.clPostVote1.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                    binding.tvPostVote1.setTextAppearance(R.style.votedTrue)
                }
                if (isVoteSecond) {
                    binding.clPostVote2.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                    binding.tvPostVote2.setTextAppearance(R.style.votedFalse)
                }

                binding.clPostVote2.setOnClickListener {
                    if (!response.voteResponseList[1].checked) {
                        setVoteSystem(response.voteResponseList[1].vote_id, false)
                        setVoteSystem(response.voteResponseList[0].vote_id, true)
                    }
                }

                binding.clPostVote1.setOnClickListener {
                    if (!isVoteFirst) {
                        setVoteSystem(response.voteResponseList[0].vote_id , false)
                        setVoteSystem(response.voteResponseList[1].vote_id, true)
                        isVoteFirst = true
                        isVoteSecond = false
                        voteSum1++
                        if (voteSum2 != 0) {
                            voteSum2--
                        }
                        binding.clPostVote1.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                        binding.clPostVote2.background = binding.root.context.getDrawable(R.drawable.bg_vote)
                        binding.tvPostVote1.setTextAppearance(R.style.votedTrue)
                        binding.tvPostVote2.setTextAppearance(R.style.votedFalse)
                        setVotePercent(voteSum1, voteSum2)
                    }
                }

                binding.clPostVote2.setOnClickListener {
                    if (!isVoteSecond) {
                        setVoteSystem(response.voteResponseList[1].vote_id, false)
                        setVoteSystem(response.voteResponseList[0].vote_id, true)
                        isVoteFirst = false
                        isVoteSecond = true
                        if (voteSum1 != 0) {
                            voteSum1--
                        }
                        voteSum2++
                        binding.clPostVote1.background = binding.root.context.getDrawable(R.drawable.bg_vote)
                        binding.clPostVote2.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                        binding.tvPostVote1.setTextAppearance(R.style.votedFalse)
                        binding.tvPostVote2.setTextAppearance(R.style.votedTrue)
                        setVotePercent(voteSum1, voteSum2)
                    }
                }
            }
        } ?: run {
            Toast.makeText(this, "데이터를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteCommunityPost(postId: Int) {
        viewModel.deleteCommunityPost(postId)
    }

    private fun scrapCommunityPost(postId: Int) {
        viewModel.scrapCommunityPost(postId)
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
                viewModel.cancelVote(voteId)
            } else {
                viewModel.checkVote(voteId)
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
