package com.example.dazn_exoplayerproject.viewModel

import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExoPlayerViewModel : ViewModel() {
    @Inject
    lateinit var exoPlayer: ExoPlayer

    fun playVideo(url: String) {
        exoPlayer.apply {
            prepare()
            play()
        }
    }
}