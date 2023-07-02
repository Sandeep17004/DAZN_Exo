package com.example.dazn_exoplayerproject.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dazn_exoplayerproject.databinding.ListItemVideoBinding
import com.example.dazn_exoplayerproject.model.VideoListDataItem

class VideoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val screenBinding = ListItemVideoBinding.bind(itemView)
    fun bind(videoItem: VideoListDataItem, listPosition: Int, videoListAdapter: VideoListAdapter) {
        screenBinding.apply {
            videoName = videoItem.name
            root.setOnClickListener {
                videoListAdapter.onItemClicked?.invoke(listPosition)
            }
        }
    }
}