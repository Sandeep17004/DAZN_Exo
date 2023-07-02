package com.example.dazn_exoplayerproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class VideoListDataItem(
    val name: String,
    val uri: String
) : Parcelable
