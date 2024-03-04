package com.thepop.android.ui.common

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thepop.android.R
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.databinding.RvCommunityPostBinding
import com.thepop.android.ui.community.CommunityViewModel

class CommunityListAdapter(
    private val viewModel: CommunityViewModel,
    private val communityPostList: CommunityListResponse.CommunityDetailResponseList
) : RecyclerView.Adapter<CommunityListAdapter.CommunityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val binding = RvCommunityPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.bind(communityPostList.communityDetailResponseList[position])
    }

    override fun getItemCount(): Int {
        return communityPostList.communityDetailResponseList.size
    }

    fun addItems(items: List<CommunityListResponse.CommunityDetailResponseList.CommunityDetail>) {
        if (items.isNotEmpty()) {
            val mutableList = communityPostList.communityDetailResponseList.toMutableList()
            val currentSize = mutableList.size
            mutableList.addAll(items)
            communityPostList.communityDetailResponseList = mutableList

            notifyItemRangeInserted(currentSize, items.size)
        }
    }

    fun clearItems() {
        communityPostList.communityDetailResponseList = mutableListOf()
        notifyDataSetChanged()
    }


    inner class CommunityViewHolder(private val binding: RvCommunityPostBinding) : RecyclerView.ViewHolder(binding.root) {

        private var isVoteFirst = false
        private var isVoteSecond = false
        private var voteSum1 = 0
        private var voteSum2 = 0

        init {
            try {
                binding.clPostVote1.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val communityPost = communityPostList.communityDetailResponseList[position]
                        if (!isVoteFirst) {
                            setVoteSystem(communityPost.voteResponseList[0].voteId, false)
                            setVoteSystem(communityPost.voteResponseList[1].voteId, true)
                            isVoteFirst = true
                            isVoteSecond = false
                            voteSum1++
                            if (voteSum2 != 0) {
                                voteSum2--
                            }
                            binding.clPostVote1.background =
                                binding.root.context.getDrawable(R.drawable.bg_vote_true)
                            binding.clPostVote2.background =
                                binding.root.context.getDrawable(R.drawable.bg_vote)
                            binding.tvPostVote1.setTextAppearance(R.style.votedTrue)
                            binding.tvPostVote2.setTextAppearance(R.style.votedFalse)
                            setVotePercent(voteSum1, voteSum2)
                        }
                    }
                }

                binding.clPostVote2.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val communityPost = communityPostList.communityDetailResponseList[position]
                        if (!isVoteSecond) {
                            setVoteSystem(communityPost.voteResponseList[1].voteId, false)
                            setVoteSystem(communityPost.voteResponseList[0].voteId, true)

                            isVoteFirst = false
                            isVoteSecond = true
                            if (voteSum1 != 0) {
                                voteSum1--
                            }
                            voteSum2++
                            binding.clPostVote1.background =
                                binding.root.context.getDrawable(R.drawable.bg_vote)
                            binding.clPostVote2.background =
                                binding.root.context.getDrawable(R.drawable.bg_vote_true)
                            binding.tvPostVote1.setTextAppearance(R.style.votedFalse)
                            binding.tvPostVote2.setTextAppearance(R.style.votedTrue)
                            setVotePercent(voteSum1, voteSum2)
                        }
                    }
                }

                binding.root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val communityPost = communityPostList.communityDetailResponseList[position]
                        goToCommunityDetail(communityPost.communityId)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("CommunityViewHolder", e.toString())
            }
        }
        fun bind(communityPost: CommunityListResponse.CommunityDetailResponseList.CommunityDetail) {
            binding.tvPostContentsTitle.text = communityPost.title
            if (communityPost.description.length > 60) {
                binding.tvPostContentsContents.text = communityPost.description.substring(0, 60) + "..."
            } else {
                binding.tvPostContentsContents.text = communityPost.description
            }
            if (communityPost.imageList.isEmpty()) {
                binding.ivContents.visibility = android.view.View.GONE
            } else {
                Glide.with(binding.root.context)
                    .load(communityPost.imageList[0])
                    .into(binding.ivContents)
            }
            binding.ivPostBadge.visibility = if (communityPost.userType == "BUSINESS") android.view.View.VISIBLE else android.view.View.GONE
            binding.tvPostInfoUserName.text = communityPost.nickname
            binding.tvPostInfoDate.text = communityPost.createdDate
            binding.tvPopupTag.text = communityPost.categoryDescription

            val voteResponseList = communityPost.voteResponseList
            if (voteResponseList != null) {
                // voteResponseList가 null이 아닌 경우에만 처리
                if (voteResponseList.size >= 2) {
                    binding.clPostVote.visibility = android.view.View.VISIBLE
                    binding.tvPostVote1.text = voteResponseList[0].content
                    binding.tvPostVote2.text = voteResponseList[1].content
                    isVoteFirst = voteResponseList[0].checked
                    isVoteSecond = voteResponseList[1].checked
                    voteSum1 = voteResponseList[0].voteSum
                    voteSum2 = voteResponseList[1].voteSum

                    if (isVoteFirst or isVoteSecond) {
                        setVotePercent(voteSum1, voteSum2)
                    }
                    if (isVoteFirst) {
                        binding.clPostVote1.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                        binding.tvPostVote1.setTextAppearance(R.style.votedTrue)
                    }
                    if (isVoteSecond) {
                        binding.clPostVote2.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                        binding.tvPostVote2.setTextAppearance(R.style.votedTrue)
                    }
                }
            } else {
                // voteResponseList가 null인 경우에 대한 처리
                binding.clPostVote.visibility = android.view.View.GONE
            }



        }

        private fun goToCommunityDetail(postId: Int) {
            val intent = Intent(binding.root.context, CommunityDetailActivity::class.java)
            intent.putExtra("postId", postId)
            binding.root.context.startActivity(intent)
        }

        private fun setVoteSystem(voteId: Int, isVoted: Boolean) {
            if (isVoted) {
                viewModel.cancelVote(voteId)
            } else {
                viewModel.checkVote(voteId)
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
}