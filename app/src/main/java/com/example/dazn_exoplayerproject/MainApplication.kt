package com.example.dazn_exoplayerproject

import android.app.Application
import com.example.dazn_exoplayerproject.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                koinModules
            )
        }
    }
}