package com.thepop.android.ui.mypage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.thepop.android.R
import com.thepop.android.databinding.FragmentMyPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : Fragment() {

    companion object {
        fun newInstance() = MyPageFragment()
    }

    private lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabs()
    }


    private fun setTabs() {
        binding.vpMypage.adapter = MypagePagerAdapter(requireActivity())
        binding.vpMypage.isUserInputEnabled = false
        TabLayoutMediator(binding.tlMypage, binding.vpMypage) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.mypage_tab1)
                else -> getString(R.string.mypage_tab2)
            }
        }.attach()
    }

}