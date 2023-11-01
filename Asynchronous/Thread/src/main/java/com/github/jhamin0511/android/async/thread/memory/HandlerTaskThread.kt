package com.github.jhamin0511.android.async.thread.memory

import android.os.Handler
import android.os.Looper
import android.os.Message

class HandlerTaskThread : Thread("PostTask ${Counter.getCount()}") {
    private lateinit var handler: Handler

    override fun run() {
        Looper.prepare()
        handler = object : Handler(Looper.myLooper() ?: Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
            }
        }
        Looper.loop()
    }

    fun doPost() {
        handler.post {
            sleep(Counter.time)
        }
    }
}
