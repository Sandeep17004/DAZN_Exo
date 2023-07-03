package com.example.dazn_exoplayerproject

import com.example.dazn_exoplayerproject.model.ExoPlayerActionEvents
import com.example.dazn_exoplayerproject.ui.VideoPlayerFragment
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VideoPlayerFragmentTest {
    @Test
    fun testPauseCountIsZeroWhenFragmentIsCreated() {
        // Create a `VideoPlayerFragment` instance.
        val fragment = VideoPlayerFragment()

        // Assert that the `pauseCount` variable is 0.
        assertEquals(0, fragment.pauseCount)
    }

    @Test
    fun testPauseCountIsIncrementedByOneWhenPauseButtonClicked() {
        // Create a `VideoPlayerFragment` instance.
        val fragment = VideoPlayerFragment()

        // Simulate the `PauseButtonClicked` event.
        fragment.onExoPlayerPlaybackOptionsClicked(ExoPlayerActionEvents.PauseButtonClicked)

        // Assert that the `pauseCount` variable is incremented by 1.
        assertEquals(1, fragment.pauseCount)
    }

    @Test
    fun testPauseCountIsResetToZeroWhenFragmentIsDestroyed() {
        // Create a `VideoPlayerFragment` instance.
        val fragment = VideoPlayerFragment()

        // Simulate the `PauseButtonClicked` event.
        fragment.onExoPlayerPlaybackOptionsClicked(ExoPlayerActionEvents.PauseButtonClicked)

        // Destroy the `VideoPlayerFragment` instance.
        fragment.onDestroyView()

        // Assert that the `pauseCount` variable is reset to 0.
        assertEquals(0, fragment.pauseCount)
    }
}
