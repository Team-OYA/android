package com.thepop.android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thepop.android.databinding.VpAdImageBinding

class HomeMainPagerAdapter (private var adImageList: ArrayList<Int>) : RecyclerView.Adapter<HomeMainPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VpAdImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = adImageList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return adImageList.size
    }

    inner class ViewHolder(private val binding: VpAdImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item)
                    .into(vpAdImage)
            }
        }
    }
}
