package com.github.jhamin0511.android.async.service.basic

import android.app.Service
import android.content.Intent

class BasicService : Service() {

    override fun onBind(intent: Intent?) = null

    override fun onCreate() {
        println("OnCreate()")
    }

    override fun onDestroy() {
        println("onDestroy()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("onStartCommand()")
        return START_REDELIVER_INTENT
    }
}
