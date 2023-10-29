package com.github.jhamin0511.android.async.handler.looper

import android.os.Handler
import com.github.jhamin0511.android.async.handler.monitor.LogHandler

class NoneLooperThread(
    private val monitorLogHandler: LogHandler
) : Thread() {
    private lateinit var handler: Handler

    override fun run() {
        try {
            handler = Handler()
        } catch (e: Throwable) {
            monitorLogHandler.sendException(e.toString())
        }
    }

    fun exit() {
        try {
            handler.looper.quit()
        } catch (e: Throwable) {
            monitorLogHandler.sendException(e.toString())
        }
    }
}
