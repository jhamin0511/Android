package com.github.jhamin0511.android.async.handler.looper

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView

class NoneLooperHandler(
    private val monitor: TextView
) : Handler(Looper.getMainLooper()) {
    companion object {
        private const val EXCEPTION = 1000
    }

    private val logger = TextMonitorLogger()

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            EXCEPTION -> {
                if (msg.obj is Throwable) {
                    val original = monitor.text
                    val newMessage = msg.obj.toString()

                    monitor.text = logger.combineMessage(original, newMessage)

                    val scrollAmount = monitor.layout.getLineTop(monitor.lineCount) - monitor.height
                    val scrollYPosition = if (scrollAmount > 0) {
                        scrollAmount + monitor.compoundPaddingTop + monitor.compoundPaddingBottom
                    } else {
                        0
                    }
                    monitor.scrollTo(0, scrollYPosition)
                }
            }

            else -> throw IllegalArgumentException("not support!")
        }
    }


    fun sendException(e: RuntimeException) {
        val message = obtainMessage(EXCEPTION)
        message.obj = e
        sendMessage(message)
    }
}
