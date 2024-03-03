package com.thepop.android.ui.home

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.transition.Transition
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.thepop.android.databinding.VpAdImageBinding
import com.thepop.android.util.BlurUtil

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
                // Glide를 사용하여 이미지 설정
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(item)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                        ) {
                            vpAdImage.setImageResource(item)
                            var blurredBitmap
                                    = BlurUtil.blur(itemView.context, resource, 25f)
                            for (i in 0..8) {
                                blurredBitmap = BlurUtil.blur(itemView.context, blurredBitmap, 25f)
                            }

                            ivBlur.setImageBitmap(blurredBitmap)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            // 이미지 로드가 취소되면 여기에 대한 처리를 추가할 수 있습니다.
                        }
                    })
            }
        }
    }





}
