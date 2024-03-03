package com.thepop.android.ui.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thepop.android.R
import com.thepop.android.data.model.community.CommunityListResponse
import com.thepop.android.databinding.FragmentCommunityMainBinding
import com.thepop.android.ui.common.CommunityListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityMainFragment : Fragment() {

    private lateinit var binding: FragmentCommunityMainBinding
    private val communityViewModel by viewModels<CommunityViewModel>()
    private var pageNo = 0
    private var type = "all"
    private lateinit var adapter: CommunityListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun goWrite() {
        binding.fabCommunity.setOnClickListener {
            val intent = Intent(context, WriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getCommunityList() {
        when (binding.rgCommunity.checkedRadioButtonId) {
            R.id.rb_community_all -> {
                type = "all"
                communityViewModel.getCommunityPostList(type, pageNo, 10)
            }
            R.id.rb_community_ad -> {
                type = "business"
                communityViewModel.getCommunityPostList(type, pageNo, 10)
            }
            R.id.rb_community_user -> {
                type = "user"
                communityViewModel.getCommunityPostList(type, pageNo, 10)
            }
        }

    }

    private fun dataObserver() {
        communityViewModel.communityPostList.observe(viewLifecycleOwner) {
            setCommunityList(it)
        }

        communityViewModel.communityAdditionPostList.observe(viewLifecycleOwner) {
            Log.e("dataObserver", it.toString())
            try {
                adapter.addItems(it.communityDetailResponseList)
            } catch (e: Exception) {
                Log.e("dataObserver", e.toString())
                e.printStackTrace()
            }
        }
    }

    private fun setCommunityList(data: CommunityListResponse.CommunityDetailResponseList) {
        val recyclerViewList = binding.rvCommunityPost
        adapter = CommunityListAdapter(communityViewModel, data)
        recyclerViewList.layoutManager = GridLayoutManager(
            requireContext(), 1, GridLayoutManager.VERTICAL, false
        )
        recyclerViewList.adapter = adapter
    }

    private fun init() {
        binding.rbCommunityAll.setOnClickListener { onRadioButtonClicked(binding.rbCommunityAll) }
        binding.rbCommunityAd.setOnClickListener { onRadioButtonClicked(binding.rbCommunityAd) }
        binding.rbCommunityUser.setOnClickListener { onRadioButtonClicked(binding.rbCommunityUser) }
        pageNo = 0
        goWrite()
        getCommunityList()
        dataObserver()
        scrollListener()
    }

    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            if (checked) {
                when (view.id) {
                    R.id.rb_community_all -> {
                        pageNo = 0
                        type = "all"
                        binding.fabCommunity.visibility = View.VISIBLE
                        communityViewModel.getCommunityPostList(type, pageNo, 10)
                    }
                    R.id.rb_community_ad -> {
                        pageNo = 0
                        type = "business"
                        binding.fabCommunity.visibility = View.GONE
                        communityViewModel.getCommunityPostList(type, pageNo, 10)
                    }
                    R.id.rb_community_user -> {
                        pageNo = 0
                        type = "user"
                        binding.fabCommunity.visibility = View.VISIBLE
                        communityViewModel.getCommunityPostList(type, pageNo, 10)
                    }
                }
            }
        }
    }
    private fun scrollListener() {
        val onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    pageNo += 1
                    communityViewModel.getPaginationCommunityPostList(type, pageNo, 10)
                }
            }
        }

        binding.rvCommunityPost.clearOnScrollListeners()

        binding.rvCommunityPost.addOnScrollListener(onScrollListener)
    }

    override fun onStop() {
        super.onStop()
        init()
    }

    override fun onPause() {
        super.onPause()
        init()
    }

    override fun onResume() {
        super.onResume()
        init()
    }

}
