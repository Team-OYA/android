package com.thepop.android.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.thepop.android.databinding.FragmentCommunityDetailBinding
import com.thepop.android.util.DialogUtil

class CommunityDetailFragment : Fragment() {

    private lateinit var binding: FragmentCommunityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentCommunityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialog()
    }

    private fun setDialog() {
        val dialog = DialogUtil(this.requireActivity())
        binding.btnMore.setOnClickListener {
            dialog.show {
                if (it) {
                    Toast.makeText(context, "Dialog OK", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Dialog Cancel", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}