package com.thepop.android.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thepop.android.databinding.VpImageSliderBinding

class CommunityDetailIVpAdapter(private var postImageList: List<String>) : RecyclerView.Adapter<CommunityDetailIVpAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VpImageSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = postImageList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return postImageList.size
    }

    inner class ViewHolder(private val binding: VpImageSliderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item)
                    .into(ivPostImage)
            }
        }
    }
}