package com.app.thepunjabifeast.presentation

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) return
        // Forward WARN, ERROR, etc. to Crashlytics
        FirebaseCrashlytics.getInstance().log("$priority/$tag: $message")
        t?.let { FirebaseCrashlytics.getInstance().recordException(it) }
    }
}
