package com.example.dazn_exoplayerproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.dazn_exoplayerproject.R
import com.example.dazn_exoplayerproject.model.VideoListDataItem

class VideoListAdapter :
    ListAdapter<VideoListDataItem, VideoItemViewHolder>(VideoItemDiffCallback()) {
    var onItemClicked: ((listPosition: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_video, parent, false)
        return VideoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int) {
        val videoItem = getItem(position)
        holder.bind(videoItem, position, this)
    }
}