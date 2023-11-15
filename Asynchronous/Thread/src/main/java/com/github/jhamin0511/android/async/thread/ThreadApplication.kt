package com.github.jhamin0511.android.async.thread

import android.app.Application
import timber.log.Timber

class ThreadApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}
