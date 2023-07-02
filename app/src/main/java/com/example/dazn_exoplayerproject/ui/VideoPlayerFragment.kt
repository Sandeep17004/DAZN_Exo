package com.example.dazn_exoplayerproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.dazn_exoplayerproject.databinding.FragmentVideoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import org.koin.android.ext.android.inject

class VideoPlayerFragment : Fragment(), Player.Listener {
    private val args: VideoPlayerFragmentArgs by navArgs()
    private val exoPlayer: ExoPlayer by inject()
    private lateinit var screenBinding: FragmentVideoPlayerBinding

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
        screenBinding.exoPlayView.player = exoPlayer
        exoPlayer.addListener(this)
        val videoUrl = args.videoList[args.listPosition].uri
        playVideo(videoUrl)
    }

    override fun onStart() {
        super.onStart()
        exoPlayer.playWhenReady = true
    }

    private fun playVideo(videoUrl: String) {
        val mediaItem = MediaItem.fromUri(videoUrl)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
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
        screenBinding.exoPlayView.player = null
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                screenBinding.isVideoBuffering = true
            }

            Player.STATE_READY -> {
                screenBinding.isVideoBuffering = false
            }

            Player.STATE_ENDED -> {
                Toast.makeText(requireContext(), "end", Toast.LENGTH_SHORT).show()

            }
        }
    }
}