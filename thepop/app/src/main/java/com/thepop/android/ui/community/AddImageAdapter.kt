package com.thepop.android.ui.community

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thepop.android.databinding.RvAddImageBinding

class AddImageAdapter(private var addImageList: ArrayList<Uri>) : RecyclerView.Adapter<AddImageAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddImageAdapter.ViewHolder {
        val binding = RvAddImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddImageAdapter.ViewHolder, position: Int) {
        val item = addImageList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return addImageList.size
    }

    inner class ViewHolder(private val binding: RvAddImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Uri) {
            Glide.with(binding.root.context)
                .load(item)
                .into(binding.ivAddPhoto)
        }
    }


}