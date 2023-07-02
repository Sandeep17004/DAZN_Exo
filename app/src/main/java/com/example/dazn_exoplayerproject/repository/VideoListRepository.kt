package com.example.dazn_exoplayerproject.repository

import com.example.dazn_exoplayerproject.model.VideoListDataItem

interface VideoListRepository {
    suspend fun readVideoJsonList(): List<VideoListDataItem>
}