package com.example.dazn_exoplayerproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dazn_exoplayerproject.model.ExoPlayerActionEvents
import com.example.dazn_exoplayerproject.model.VideoListDataItem
import com.example.dazn_exoplayerproject.repository.FireBaseRepository
import com.example.dazn_exoplayerproject.repository.VideoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class ExoPlayerViewModel(
    private val videoListRepository: VideoListRepository,
    private val firebaseRepository: FireBaseRepository,
) : ViewModel(), KoinComponent {
    val videoList = MutableLiveData<List<VideoListDataItem>>()

    init {
        loadVideoList()
    }

    private fun loadVideoList() {
        viewModelScope.launch(Dispatchers.Default) {
            videoList.postValue(videoListRepository.readVideoJsonList())
        }
    }

    fun sendFireBaseEvents(exoPlayerActionEvents: ExoPlayerActionEvents) {
        firebaseRepository.sendEvent(exoPlayerActionEvents)
    }
}