package com.github.jhamin0511.android.async.handler.looper

import android.os.Handler
import com.github.jhamin0511.android.async.handler.monitor.LogHandler

object NoneLooperThreadCounter {
    var counter = 1
}

class NoneLooperThread(
    private val monitorLogHandler: LogHandler
) : Thread("NoneLooperThread #${NoneLooperThreadCounter.counter++}") {
    private lateinit var handler: Handler

    override fun run() {
        try {
            handler = Handler()
        } catch (e: Throwable) {
            monitorLogHandler.sendLog(e.toString())
        }
    }

    fun exit() {
        try {
            handler.looper.quit()
        } catch (e: Throwable) {
            monitorLogHandler.sendLog(e.toString())
        }
    }
}
