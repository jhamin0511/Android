package com.github.jhamin0511.android.async.handler

import android.os.Handler
import android.os.Looper
import android.os.Message

class LooperThread : Thread() {

    companion object {
        private const val DELAYED_DURATION: Long = 5_000
        private const val WHAT_MESSAGE = 1
        private const val WHAT_MESSAGE_DELAYED = 2
    }

    private lateinit var handler: Handler

    override fun run() {
        Looper.prepare()
        handler = object : Handler(Looper.myLooper() ?: Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val type = when (msg.what) {
                    WHAT_MESSAGE -> "HandleMessage"
                    else -> "HandleMessageDelayed"
                }
                println("[$type][${currentThread()}] : ${msg.obj}")
            }
        }
        Looper.loop()
    }

    fun addIdleHandler(value: Int) {
        handler.looper.queue.addIdleHandler {
            println("[IdleHandler#$value][${currentThread()}]")
            Thread.sleep((value % 5) * 1000L)
            System.currentTimeMillis().toString().last() == '0'
        }
    }

    fun sendMessage(value: Int) {
        val message = handler.obtainMessage(WHAT_MESSAGE, value)
        handler.sendMessage(message)
    }

    fun sendMessageDelayed(value: Int) {
        val message = handler.obtainMessage(WHAT_MESSAGE_DELAYED, value)
        handler.sendMessageDelayed(message, DELAYED_DURATION)
    }

    fun post(value: Int) {
        handler.post {
            println("[Post][${currentThread()}] : $value")
        }
    }

    fun postDelayed(value: Int) {
        handler.postDelayed({
            println("[PostDelayed][${currentThread()}] : $value")
        }, DELAYED_DURATION)
    }

    fun quit() {
        handler.looper.quit()
    }
}
