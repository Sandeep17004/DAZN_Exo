package com.example.dazn_exoplayerproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dazn_exoplayerproject.databinding.FragmentVideoListBinding
import com.example.dazn_exoplayerproject.model.VideoListDataItem
import com.example.dazn_exoplayerproject.viewModel.ExoPlayerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoListFragment : Fragment() {
    private val exoPlayerViewModel: ExoPlayerViewModel by viewModel()
    private lateinit var screenBinding: FragmentVideoListBinding
    private val videoListAdapter by lazy { }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentVideoListBinding.inflate(inflater).also { screenBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        exoPlayerViewModel.videoList.observe(viewLifecycleOwner, ::renderVideoList)
    }

    private fun renderVideoList(videoListDataItems: List<VideoListDataItem>) {
        if (videoListDataItems.isNotEmpty()) {

        }
    }

}