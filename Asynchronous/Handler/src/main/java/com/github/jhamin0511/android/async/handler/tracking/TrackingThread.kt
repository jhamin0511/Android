package com.github.jhamin0511.android.async.handler.tracking

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.util.LogPrinter

object TrackingThreadCounter {
    var count = 1
}

class TrackingThread : Thread(
    "TrackingThread #${TrackingThreadCounter.count++}"
) {
    companion object {
        private val TAG = TrackingThread::class.simpleName
    }

    private lateinit var handler: Handler

    override fun run() {
        Looper.prepare()
        handler = object : Handler(Looper.myLooper() ?: Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val message = "handleMessage - what = ${msg.what}"
                println(message)
            }
        }
        Looper.myLooper()?.setMessageLogging(LogPrinter(Log.DEBUG, TAG))
        Looper.loop()
    }

    fun sendMessage() {
        handler.sendEmptyMessageDelayed(1, 2000)
        handler.sendEmptyMessage(2)
        handler.obtainMessage(3, 0, 0, Any()).sendToTarget()
        handler.sendEmptyMessageDelayed(4, 300)
        handler.postDelayed({
            println("Execute")
        }, 400)
        handler.sendEmptyMessage(5)
    }
}
