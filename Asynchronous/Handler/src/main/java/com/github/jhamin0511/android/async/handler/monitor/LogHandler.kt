package com.github.jhamin0511.android.async.handler.monitor

import android.os.Handler
import android.os.Looper
import android.os.Message

class LogHandler(
    private val textView: LogTextView
) : Handler(Looper.getMainLooper()) {
    companion object {
        private const val LOG = 1000
    }

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            LOG -> {
                if (msg.obj is String) {
                    val newMessage = msg.obj.toString()
                    textView.setLog(newMessage)
                }
            }

            else -> throw IllegalArgumentException("not support!")
        }
    }

    fun sendException(log: String) {
        val message = obtainMessage(LOG)
        message.obj = log
        sendMessage(message)
    }
}
