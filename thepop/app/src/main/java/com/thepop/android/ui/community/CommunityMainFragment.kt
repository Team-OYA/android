package com.thepop.android.ui.community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thepop.android.R
import com.thepop.android.databinding.FragmentCommunityMainBinding

class CommunityMainFragment : Fragment() {

    private lateinit var binding: FragmentCommunityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCommunityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goWrite()
    }

    private fun goWrite() {
        binding.fabCommunity.setOnClickListener {
            val intent = Intent(context, WriteActivity::class.java)
            startActivity(intent)
        }
    }

}