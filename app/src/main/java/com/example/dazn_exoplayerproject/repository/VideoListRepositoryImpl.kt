package com.example.dazn_exoplayerproject.repository

import android.content.Context
import com.example.dazn_exoplayerproject.model.VideoListDataItem
import com.google.gson.Gson
import java.io.IOException

class VideoListRepositoryImpl(private val context: Context) :
    VideoListRepository {
    override suspend fun readVideoJsonList(): List<VideoListDataItem> {
        val jsonFileName = "video_list.json"
        val jsonString: String = try {
            val inputStream = context.assets.open(jsonFileName.trimIndent())
            inputStream.bufferedReader().use { it.readText() }

        } catch (exception: IOException) {
            ""
        }
        val gson = Gson()
        return gson.fromJson(jsonString, Array<VideoListDataItem>::class.java).toList()
    }
}