package com.github.jhamin0511.android.async.handler.indicate

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.IllegalArgumentException

class IndicateHandler(
    private val progress: ProgressBar,
    private val textView: TextView,
    private val start: Button
) : Handler(Looper.getMainLooper()) {
    companion object {
        private const val START = 1000
        private const val FINISH = 2000
        private const val SEND = 3000
        private const val PERCENT_FORMAT = "%d%%"
    }

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            START -> {
                textView.text = PERCENT_FORMAT.format(0)
                progress.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
                start.visibility = View.GONE
            }

            FINISH -> {
                progress.visibility = View.GONE
                textView.visibility = View.GONE
                start.visibility = View.VISIBLE
            }

            SEND -> {
                textView.text = PERCENT_FORMAT.format(msg.arg1)
            }

            else -> throw IllegalArgumentException("not support!")
        }
    }

    fun startProgress() {
        val message = obtainMessage(START)
        sendMessage(message)
    }

    fun sendProgress(value: Int) {
        val message = obtainMessage(SEND, value, 0)
        sendMessage(message)
    }

    fun finishProgress() {
        val message = obtainMessage(FINISH)
        sendMessage(message)
    }
}
