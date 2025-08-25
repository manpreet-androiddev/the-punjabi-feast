package com.app.thepunjabifeast

import android.app.Application
import com.app.thepunjabifeast.presentation.CrashReportingTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PunjabiFeastApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree()) // Logs to Logcat
        } else {
            // Optional: Add crash reporting tree for release
            Timber.plant(CrashReportingTree())
        }
    }
}