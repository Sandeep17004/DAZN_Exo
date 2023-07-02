package com.example.dazn_exoplayerproject.di

import android.content.Context
import com.example.dazn_exoplayerproject.repository.VideoListRepository
import com.example.dazn_exoplayerproject.repository.VideoListRepositoryImpl
import com.example.dazn_exoplayerproject.viewModel.ExoPlayerViewModel
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import org.koin.dsl.module


val exoPlayerModule = module {
    factory {
        ExoPlayer.Builder(get<Context>()).build().apply {
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
        }
    }
}

val repositoryModule = module {
    single<VideoListRepository> { VideoListRepositoryImpl(get()) }
}

val viewModelModule = module {
    single { ExoPlayerViewModel(get<VideoListRepository>()) }
}

val koinModules = listOf(
    exoPlayerModule,
    repositoryModule,
    viewModelModule
)


