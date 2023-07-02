package com.example.dazn_exoplayerproject.viewModel

import androidx.lifecycle.LiveData
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

    private val _pauseCount = MutableLiveData<Int>()
    val pauseCount: LiveData<Int> get() = _pauseCount

    private val _playCount = MutableLiveData<Int>()
    val playCount: LiveData<Int> get() = _playCount

    private val _nextCount = MutableLiveData<Int>()
    val nextCount: LiveData<Int> get() = _nextCount

    private val _previousCount = MutableLiveData<Int>()
    val previousCount: LiveData<Int> get() = _previousCount

    init {
        loadVideoList()
    }

    private fun loadVideoList() {
        viewModelScope.launch(Dispatchers.Default) {
            videoList.postValue(videoListRepository.readVideoJsonList())
        }
    }

    fun incrementPauseCount() {
        _pauseCount.value = (_pauseCount.value ?: 0) + 1
    }

    fun incrementPlayCount() {
        _playCount.value = (_playCount.value ?: 0) + 1
    }

    fun incrementNextCount() {
        _nextCount.value = (_nextCount.value ?: 0) + 1
    }

    fun incrementPreviousCount() {
        _previousCount.value = (_previousCount.value ?: 0) + 1
    }


    fun sendFireBaseEvents(exoPlayerActionEvents: ExoPlayerActionEvents) {
        firebaseRepository.sendEvent(exoPlayerActionEvents)
    }

    fun resetCounters() {
        _pauseCount.value = 0
        _playCount.value = 0
        _nextCount.value = 0
        _previousCount.value = 0
    }
}