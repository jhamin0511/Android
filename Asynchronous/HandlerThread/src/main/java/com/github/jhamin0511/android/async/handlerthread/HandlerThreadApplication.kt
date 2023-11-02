package com.github.jhamin0511.android.async.handlerthread

import android.app.Application
import timber.log.Timber

class HandlerThreadApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}
