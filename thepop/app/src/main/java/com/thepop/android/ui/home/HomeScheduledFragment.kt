package com.thepop.android.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thepop.android.databinding.FragmentHomeScheduledBinding

class HomeScheduledFragment : Fragment() {

    private lateinit var binding: FragmentHomeScheduledBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScheduledBinding.inflate(inflater, container, false)
        return binding.root
    }
}