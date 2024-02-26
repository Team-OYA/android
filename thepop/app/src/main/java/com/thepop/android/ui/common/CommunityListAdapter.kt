package com.thepop.android.ui.common

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thepop.android.R
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.databinding.RvCommunityPostBinding

class CommunityListAdapter(private val communityPostList: CommunityListResponse.CommunityDetailResponseList):
    RecyclerView.Adapter<CommunityListAdapter.CommunityViewHolder>() {

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

    class CommunityViewHolder(private val binding: RvCommunityPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(communityPost: CommunityListResponse.CommunityDetailResponseList.CommunityDetail) {
            binding.tvPostContentsTitle.text = communityPost.title
            binding.tvPostContentsContents.text = communityPost.description
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

            if ((communityPost.voteResponseList?.size ?: 0) >= 2) {
                binding.clPostVote.visibility = android.view.View.VISIBLE
                binding.tvPostVote1.text = communityPost.voteResponseList?.get(0)?.content
                binding.tvPostVote2.text = communityPost.voteResponseList?.get(1)?.content
                if (communityPost.voteResponseList?.get(0)?.checked == true) {
                    binding.clPostVote1.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                    binding.tvPostVotePercent1.visibility = android.view.View.VISIBLE
                    binding.tvPostVotePercent2.visibility = android.view.View.VISIBLE
                }
                if (communityPost.voteResponseList?.get(1)?.checked == true) {
                    binding.clPostVote2.background = binding.root.context.getDrawable(R.drawable.bg_vote_true)
                    binding.tvPostVotePercent1.visibility = android.view.View.VISIBLE
                    binding.tvPostVotePercent2.visibility = android.view.View.VISIBLE
                }
            }


            binding.root.setOnClickListener {
                goToCommunityDetail(communityPost.communityId)
            }
        }

        private fun goToCommunityDetail(postId: Int) {
            val intent = Intent(binding.root.context, CommunityDetailActivity::class.java)
            intent.putExtra("postId", postId)
            binding.root.context.startActivity(intent)
        }
    }
}