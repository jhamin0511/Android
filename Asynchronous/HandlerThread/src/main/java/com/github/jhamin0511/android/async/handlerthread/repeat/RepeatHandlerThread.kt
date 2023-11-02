package com.github.jhamin0511.android.async.handlerthread.repeat

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.os.SystemClock
import com.github.jhamin0511.android.async.handlerthread.ThreadInfo
import timber.log.Timber

class RepeatHandlerThread : HandlerThread("Repeat ${ThreadInfo.counting()}") {
    private lateinit var handler: Handler

    override fun onLooperPrepared() {
        handler = object : Handler(looper) {
            override fun handleMessage(msg: Message) {
                process("MESSAGE")
            }
        }
    }

    // 데이터 메시지를 보내는 공개 메서드
    fun send() {
        val msg = handler.obtainMessage()
        handler.sendMessage(msg)
    }

    // 태스크 메시지를 보내는 공개 메서드
    fun post() {
        handler.post {
            process("RUNNABLE")
        }
    }

    private var processCounting = 1

    private fun process(type: String, processCounting: Int = this.processCounting++) {
        var counting = 0

        while (counting < 100) {
            counting += 25
            val message = "[${type}][Process#${processCounting}] $counting"
            Timber.d(message)
            listeners.forEach {
                it.onResult(message)
            }
            SystemClock.sleep(1000)
        }
    }

    private val listeners: MutableList<RepeatListener> = mutableListOf()

    fun register(listener: RepeatListener) {
        listeners.add(listener)
    }

    fun unregister(listener: RepeatListener) {
        listeners.remove(listener)
    }
}
