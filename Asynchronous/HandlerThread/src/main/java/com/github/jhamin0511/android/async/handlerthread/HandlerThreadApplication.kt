package com.github.jhamin0511.android.async.handlerthread

import android.app.Application
import android.content.Context
import timber.log.Timber

class HandlerThreadApplication : Application() {

    companion object {
        lateinit var instance: HandlerThreadApplication

        fun Context(): Context {
            return instance.applicationContext
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}
