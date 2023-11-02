package com.github.jhamin0511.android.async.handlerthread.resource

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.os.SystemClock
import com.github.jhamin0511.android.async.handlerthread.ThreadInfo

class ResourceHandlerThread : HandlerThread("Resource ${ThreadInfo.counting()}") {
    private val preferences = Preferences.countingSharedPreferences
    private lateinit var handler: Handler
    private var listener: ResourceListener? = null

    companion object {
        private const val PLUS = 1000
        private const val MINUS = 2000
    }

    override fun onLooperPrepared() {
        handler = object : Handler(looper) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    PLUS -> processPlus()
                    MINUS -> processMinus()
                }
            }
        }
    }

    private fun processPlus() {
        preferences.write(1)
        val result = preferences.read().toString()
        listener?.onResult(result)
        SystemClock.sleep(500)
    }

    private fun processMinus() {
        preferences.write(-1)
        val result = preferences.read().toString()
        listener?.onResult(result)
        SystemClock.sleep(500)
    }

    fun sendPlus() {
        val message = handler.obtainMessage(PLUS)
        handler.sendMessage(message)
    }

    fun sendMinus() {
        val message = handler.obtainMessage(MINUS)
        handler.sendMessage(message)
    }

    fun postPlus() {
        handler.post {
            processPlus()
        }
    }

    fun postMinus() {
        handler.post {
            processMinus()
        }
    }

    fun register(listener: ResourceListener) {
        this.listener = listener
    }
}
