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

class MyPageFragment : Fragment() {

    companion object {
        fun newInstance() = MyPageFragment()
    }

    private lateinit var viewModel: MyPageViewModel
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

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MyPageViewModel::class.java]
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