package com.thepop.android.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thepop.android.data.model.popup.PopupListResponse
import com.thepop.android.databinding.RvPopUpRecommendBinding

class PopupRecommendAdapter (private val popupList: PopupListResponse.PopupList):
    RecyclerView.Adapter<PopupRecommendAdapter.PopupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopupViewHolder {
        val binding = RvPopUpRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopupViewHolder, position: Int) {
        holder.bind(popupList.popups[position])
    }

    override fun getItemCount(): Int {
        return popupList.popups.size
    }

    class PopupViewHolder(private val binding: RvPopUpRecommendBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(popup: PopupListResponse.PopupList.Popup) {
            binding.tvPopupTitle.text = popup.title
            binding.tvPopupContent.text = popup.description
            Glide.with(binding.root)
                .load(popup.thumbnail)
                .into(binding.ivPopup)
            binding.tvPopupStartDate.text = popup.openDate
            binding.tvPopupEndDate.text = popup.closeDate
            binding.tvPopupTag.text = popup.category.description
        }
    }
}