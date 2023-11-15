package com.github.jhamin0511.android.async.handler.looper

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.github.jhamin0511.android.async.handler.monitor.LogHandler

object LooperThreadCounter {
    var counter = 1
}

class LooperThread(
    private val monitorLogHandler: LogHandler
) : Thread("LooperThread #${LooperThreadCounter.counter++}") {

    companion object {
        private const val DELAYED_DURATION: Long = 3_000
        private const val WHAT_MESSAGE = 1
        private const val WHAT_MESSAGE_DELAYED = 2
    }

    private lateinit var handler: Handler

    override fun run() {
        Looper.prepare()
        handler = object : Handler(Looper.myLooper() ?: Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val type = when (msg.what) {
                    WHAT_MESSAGE -> "Send"
                    else -> "SendDelayed"
                }
                monitorLogHandler.sendLog("[$type][${currentThread()}] : ${msg.obj}")
            }
        }
        Looper.loop()
    }

    fun addIdleHandler(value: Int) {
        handler.looper.queue.addIdleHandler {
            monitorLogHandler.sendLog("[Idle#$value][${currentThread()}]")
            sleep((value % 5) * 1000L)
            System.currentTimeMillis().toString().last() == '0'
        }
    }

    fun send(value: Int) {
        val message = handler.obtainMessage(WHAT_MESSAGE, value)
        handler.sendMessage(message)
    }

    fun sendDelayed(value: Int) {
        val message = handler.obtainMessage(WHAT_MESSAGE_DELAYED, value)
        handler.sendMessageDelayed(message, DELAYED_DURATION)
    }

    fun post(value: Int) {
        handler.post {
            monitorLogHandler.sendLog("[Post][${currentThread()}] : $value")
        }
    }

    fun postDelayed(value: Int) {
        handler.postDelayed({
            monitorLogHandler.sendLog("[PostDelayed][${currentThread()}] : $value")
        }, DELAYED_DURATION)
    }

    fun quit() {
        handler.looper.quit()
    }
}
