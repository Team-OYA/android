package com.thepop.android.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thepop.android.R
import com.thepop.android.databinding.FragmentCommunityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment : Fragment() {

    private lateinit var binding: FragmentCommunityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragment()
    }


    private fun setFragment() {
        val fragment = CommunityMainFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_community, fragment)
        transaction.commit()
    }

}

