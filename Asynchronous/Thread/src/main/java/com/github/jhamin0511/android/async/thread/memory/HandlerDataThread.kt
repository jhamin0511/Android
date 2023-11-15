package com.github.jhamin0511.android.async.thread.memory

import android.os.Handler
import android.os.Looper
import com.github.jhamin0511.android.async.thread.Counter

class HandlerDataThread : Thread("SendData ${Counter.getCount()}") {
    private lateinit var handler: Handler

    override fun run() {
        Looper.prepare()
        handler = object : Handler(Looper.myLooper() ?: Looper.getMainLooper()) {
        }
        Looper.loop()
    }

    fun doSend() {
        val message = handler.obtainMessage().apply {
            obj = Any()
        }
        handler.sendMessageDelayed(message, Counter.time)
    }
}
