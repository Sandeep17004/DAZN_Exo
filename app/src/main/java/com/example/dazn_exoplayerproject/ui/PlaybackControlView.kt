package com.example.dazn_exoplayerproject.ui

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.SeekBar
import com.example.dazn_exoplayerproject.R
import com.example.dazn_exoplayerproject.databinding.ViewPlaybackControlBinding
import com.example.dazn_exoplayerproject.ui.interfaces.PlaybackControlListener
import com.google.android.exoplayer2.Player
import java.util.concurrent.Executors

class PlaybackControlView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var screenBinding: ViewPlaybackControlBinding
    private var player: Player? = null
    private var progressUpdateTask: Runnable? = null
    private val updateHandler = Handler(Looper.getMainLooper())
    private var playbackControlListener: PlaybackControlListener? = null

    init {
        screenBinding = ViewPlaybackControlBinding.inflate(LayoutInflater.from(context), this, true)
        setupPlaybackButtons()
        setupSeekBar()
    }

    fun setPlaybackControlListener(listener: PlaybackControlListener) {
        playbackControlListener = listener
    }

    private fun setupPlaybackButtons() {
        screenBinding.previousButton.setOnClickListener {
            playbackControlListener?.onPreviousButtonClicked()
            player?.seekToPreviousMediaItem()
        }

        screenBinding.nextButton.setOnClickListener {
            playbackControlListener?.onNextButtonClicked()
            player?.seekToNextMediaItem()
        }

        screenBinding.playButton.setOnClickListener {
            if (player?.playWhenReady == true) {
                pausePlayer()
            } else {
                playPlayer()
            }
        }
    }

    private fun pausePlayer() {
        playbackControlListener?.onPausedButtonClicked()
        player?.pause()
        updatePlaybackState(true)
    }

    private fun playPlayer() {
        playbackControlListener?.onPlayButtonClicked()
        player?.play()
        updatePlaybackState(false)
    }

    private fun setupSeekBar() {
        screenBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player?.seekTo(progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // no-op
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // no-op
            }
        })
    }

    fun setPlayer(player: Player) {
        this.player = player
        updatePlaybackState(player.playWhenReady)
        updateProgress()
    }

    private fun updatePlaybackState(playWhenReady: Boolean) {
        screenBinding.playButton.setImageResource(
            if (playWhenReady) R.drawable.arrow_play else R.drawable.arrow_pause
        )
    }

    private fun updateProgress() {
        val currentProgress = player?.currentPosition ?: 0
        val maxProgress = player?.duration ?: 0
        screenBinding.seekBar.progress = currentProgress.toInt()
        screenBinding.seekBar.max = maxProgress.toInt()
    }

    fun startProgressUpdateTask() {
        progressUpdateTask = object : Runnable {
            override fun run() {
                updateProgress()
                updateHandler.postDelayed(this, 500)
            }
        }
        progressUpdateTask?.run()
    }


    fun stopProgressUpdateTask() {
        progressUpdateTask?.let { updateHandler.removeCallbacks(it) }
    }
}
