package com.thepop.android.ui.community

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thepop.android.R
import com.thepop.android.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {

    companion object {
        fun newInstance() = CommunityFragment()
    }

    private lateinit var viewModel: CommunityViewModel
    private lateinit var binding: FragmentCommunityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommunityViewModel::class.java)

        setFragment()
    }

    private fun setFragment() {
        val fragment = CommunityMainFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_community, fragment)
        transaction.commit()
    }
}
