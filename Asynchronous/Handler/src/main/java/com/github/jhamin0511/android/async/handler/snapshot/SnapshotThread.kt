package com.github.jhamin0511.android.async.handler.snapshot

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.util.LogPrinter
import com.github.jhamin0511.android.async.handler.monitor.LogHandler
import com.github.jhamin0511.android.async.handler.monitor.LogTextView

object SnapshotThreadCounter {
    var count = 1
}

class SnapshotThread : Thread(
    "SnapshotThread #${SnapshotThreadCounter.count++}"
) {
    private lateinit var handler: Handler
    private var firstIdle = true
    override fun run() {
        Looper.prepare()
        handler = object : Handler(Looper.myLooper() ?: Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val message = "handleMessage - what = ${msg.what}"
                println(message)
            }
        }
        Looper.loop()
    }

    fun snapshotMessageQueue() {
        handler.sendEmptyMessageDelayed(1, 2000)
        handler.sendEmptyMessage(2)
        handler.obtainMessage(3, 0, 0, Any()).sendToTarget()
        handler.sendEmptyMessageDelayed(4, 300)
        handler.postDelayed({
            println("Execute")
        }, 400)
        handler.sendEmptyMessage(5)
        handler.dump(LogPrinter(Log.DEBUG, "EAT"), "")
    }
}
