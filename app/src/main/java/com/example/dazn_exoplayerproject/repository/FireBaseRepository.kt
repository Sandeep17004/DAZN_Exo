package com.example.dazn_exoplayerproject.repository

import com.example.dazn_exoplayerproject.model.ExoPlayerActionEvents

interface FireBaseRepository {
    fun sendEvent(exoPlayerActionEvents: ExoPlayerActionEvents)
}