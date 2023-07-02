package com.example.dazn_exoplayerproject.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.dazn_exoplayerproject.model.VideoListDataItem

class VideoItemDiffCallback : DiffUtil.ItemCallback<VideoListDataItem>() {
    override fun areItemsTheSame(
        oldItem: VideoListDataItem,
        newItem: VideoListDataItem
    ): Boolean {
        return oldItem.uri == newItem.uri && oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: VideoListDataItem,
        newItem: VideoListDataItem
    ): Boolean {
        return oldItem == newItem
    }
}