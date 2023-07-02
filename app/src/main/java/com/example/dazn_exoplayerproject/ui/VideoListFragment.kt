package com.example.dazn_exoplayerproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dazn_exoplayerproject.R
import com.example.dazn_exoplayerproject.databinding.FragmentVideoListBinding
import com.example.dazn_exoplayerproject.model.VideoListDataItem
import com.example.dazn_exoplayerproject.ui.adapter.VideoListAdapter
import com.example.dazn_exoplayerproject.viewModel.ExoPlayerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoListFragment : Fragment() {
    private val exoPlayerViewModel: ExoPlayerViewModel by viewModel()
    private lateinit var screenBinding: FragmentVideoListBinding
    private val videoListAdapter by lazy { VideoListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentVideoListBinding.inflate(inflater).also { screenBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVideoListAdapter()
        observeViewModel()
    }

    private fun setupVideoListAdapter() {
        screenBinding.rvVideoList.apply {
            adapter = videoListAdapter.also {
                it.onItemClicked = { listPosition ->
                    navigateToPlayVideo(listPosition)
                }
            }
            layoutManager = LinearLayoutManager(requireContext())
            val itemDecorator = DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )

            ContextCompat.getDrawable(requireContext(), R.drawable.customdivider)?.let {
                itemDecorator.setDrawable(it)
            }
            addItemDecoration(itemDecorator)
        }
    }

    private fun navigateToPlayVideo(listPosition: Int) {
        findNavController().navigate(
            VideoListFragmentDirections.actionVideoListToVideoPlayer(
                listPosition,
                videoListAdapter.currentList.toTypedArray()
            )
        )
    }

    private fun observeViewModel() {
        exoPlayerViewModel.videoList.observe(viewLifecycleOwner, ::renderVideoList)
    }

    private fun renderVideoList(videoListDataItems: List<VideoListDataItem>) {
        if (videoListDataItems.isNotEmpty()) {
            videoListAdapter.submitList(videoListDataItems)
        }
    }
}