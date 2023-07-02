package com.example.dazn_exoplayerproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.dazn_exoplayerproject.R
import com.example.dazn_exoplayerproject.databinding.FragmentVideoPlayerBinding
import com.example.dazn_exoplayerproject.ui.interfaces.PlaybackControlListener
import com.example.dazn_exoplayerproject.viewModel.ExoPlayerViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoPlayerFragment : Fragment(), Player.Listener, PlaybackControlListener {
    private val exoPlayerViewModel: ExoPlayerViewModel by viewModel()
    private val args: VideoPlayerFragmentArgs by navArgs()
    private val exoPlayer: ExoPlayer by inject()
    private lateinit var screenBinding: FragmentVideoPlayerBinding
    private var playCount: Int = 0
    private var pauseCount: Int = 0
    private var nextCount: Int = 0
    private var previousCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentVideoPlayerBinding.inflate(inflater).also { screenBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializePlayer()
    }

    private fun initializePlayer() {
        screenBinding.exoPlayerView.player = exoPlayer
        screenBinding.playbackControlView.setPlayer(exoPlayer)
        screenBinding.playbackControlView.setPlaybackControlListener(this)
        exoPlayer.addListener(this)
        createExoPlayerMedia { playVideo(it) }
    }

    private fun createExoPlayerMedia(playVideo: (List<MediaItem>) -> Unit) {
        val totalVideos = exoPlayerViewModel.videoList.value ?: arrayListOf()
        val media = totalVideos.map { MediaItem.fromUri(it.uri) }
        playVideo.invoke(media)
    }

    override fun onStart() {
        super.onStart()
        exoPlayer.playWhenReady = true
    }

    private fun playVideo(mediaItem: List<MediaItem>) {
        exoPlayer.apply {
            setMediaItems(mediaItem)
            prepare()
            seekTo(args.listPosition.toLong())
            play()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.stop()
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.pause()
    }

    private fun releasePlayer() {
        exoPlayer.removeListener(this@VideoPlayerFragment)
        exoPlayer.release()
        screenBinding.exoPlayerView.player = null
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                screenBinding.isVideoBuffering = true
            }

            Player.STATE_READY -> {
                if (playWhenReady) {
                    screenBinding.playbackControlView.startProgressUpdateTask()
                } else {
                    screenBinding.playbackControlView.stopProgressUpdateTask()
                }
                screenBinding.isVideoBuffering = false
            }
        }
    }

    override fun onPlayButtonClicked() {
        screenBinding.playCount = (++playCount).toString()
    }

    override fun onPreviousButtonClicked() {
        screenBinding.prevCount = (++previousCount).toString()
    }

    override fun onNextButtonClicked() {
        screenBinding.nextCount = (++nextCount).toString()
    }

    override fun onPausedButtonClicked() {
        screenBinding.pauseCount = (++pauseCount).toString()
    }
}
