package com.example.dazn_exoplayerproject.model

sealed class ExoPlayerActionEvents {
    object PlayButtonClicked : ExoPlayerActionEvents()
    object PreviousButtonClicked : ExoPlayerActionEvents()
    object NextButtonClicked : ExoPlayerActionEvents()
    object PauseButtonClicked : ExoPlayerActionEvents()
}
