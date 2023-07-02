package com.example.dazn_exoplayerproject.ui.interfaces

import com.example.dazn_exoplayerproject.model.ExoPlayerActionEvents

interface PlaybackControlListener {
    fun onExoPlayerPlaybackOptionsClicked(exoPlayerActionEvents: ExoPlayerActionEvents)
}