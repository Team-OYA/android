package com.thepop.android.ui.taste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thepop.android.R
import com.thepop.android.databinding.FragmentTasteLandingBinding


class TasteLandingFragment : Fragment() {

    private lateinit var binding: FragmentTasteLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasteLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTasteSelectFragment()
    }

    private fun startTasteSelectFragment() {
        val tasteSelectFragment = TasteSelectFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        binding.btnStart.setOnClickListener {
            transaction.replace(R.id.container_taste, tasteSelectFragment)
            transaction.commit()
        }
    }

}