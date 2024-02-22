package com.thepop.android.ui.taste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thepop.android.R
import com.thepop.android.databinding.FragmentTasteSelectBinding

class TasteSelectFragment : Fragment() {

    private lateinit var binding: FragmentTasteSelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasteSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

}