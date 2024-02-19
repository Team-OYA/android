package com.thepop.android.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thepop.android.databinding.FragmentOnboardingBinding

class OnboardingAdapter(private val onboardingList: List<OnboardingData>) :
    RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: FragmentOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onboardingData: OnboardingData) {
            binding.apply {
                ivOnboarding.setImageResource(onboardingData.image)
                tvOnboardingTitle.text = onboardingData.title
                tvOnboardingContent.text = onboardingData.content

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = onboardingList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return onboardingList.size
    }
}