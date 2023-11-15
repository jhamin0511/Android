package com.github.jhamin0511.android.async.handlerthread.chain

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.os.SystemClock
import com.github.jhamin0511.android.async.handlerthread.ThreadInfo
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class NetworkHandlerThread : HandlerThread("Network ${ThreadInfo.counting()}") {

    companion object {
        private const val PROCESS_DURATION = 1000L
        private const val WHAT_READY = 1000
        private const val WHAT_CONNECT = 2000
        private const val WHAT_DOWNLOAD = 3000
        private const val WHAT_FINISH = 4000
        private const val WHAT_DONE = 5000
        private const val WHAT_ERROR = 6000
    }

    private lateinit var handler: Handler

    override fun onLooperPrepared() {
        handler = object : Handler(looper) {
            override fun handleMessage(msg: Message) {
                val counting = msg.arg1

                when (msg.what) {
                    WHAT_READY -> ready(counting)
                    WHAT_CONNECT -> connect(counting)
                    WHAT_DOWNLOAD -> download(counting)
                    WHAT_FINISH -> finish(counting)
                    WHAT_DONE -> done(counting)
                    WHAT_ERROR -> error(counting)
                }
            }
        }
    }

    private var callback: NetworkCallback? = null

    fun registerCallback(listener: NetworkCallback) {
        callback = listener
    }

    private var callCounting = 0

    fun call() {
        val message = handler.obtainMessage(WHAT_READY, callCounting++, 0)
        handler.sendMessage(message)
    }

    private fun ready(counting: Int) {
        val result = "Ready #${counting}"
        callback?.onResult(result, 15)
        val message = handler.obtainMessage(WHAT_CONNECT, counting, 0)
        handler.sendMessageAtFrontOfQueue(message)
        SystemClock.sleep(PROCESS_DURATION)
    }

    private fun connect(counting: Int) {
        val result = "Connect #${counting}"
        callback?.onResult(result, 30)
        val message = handler.obtainMessage(WHAT_DOWNLOAD, counting, 0)
        handler.sendMessageAtFrontOfQueue(message)
        SystemClock.sleep(PROCESS_DURATION)
    }

    private fun download(counting: Int) {
        var progress = 40
        var step = 0

        while (step++ < 5) {
            val result = "DOWNLOAD #${counting}"
            callback?.onResult(result, progress)

            val error = Random.nextInt(100)
            if (error % 10 == 0) {
                val message = handler.obtainMessage(WHAT_ERROR, counting, 0)
                handler.sendMessageAtFrontOfQueue(message)
                return
            }

            progress += 10
            SystemClock.sleep(PROCESS_DURATION)
        }


        val message = handler.obtainMessage(WHAT_FINISH, counting, 0)
        handler.sendMessageAtFrontOfQueue(message)
    }

    private fun finish(counting: Int) {
        val result = "FINISH #${counting}"
        callback?.onResult(result, 90)
        val message = handler.obtainMessage(WHAT_DONE, counting, 0)
        handler.sendMessageAtFrontOfQueue(message)
        SystemClock.sleep(PROCESS_DURATION)
    }

    private fun done(counting: Int) {
        val result = "DONE #${counting}"
        callback?.onResult(result, 100)
        SystemClock.sleep(PROCESS_DURATION + TimeUnit.SECONDS.toMillis(2))
    }

    private fun error(counting: Int) {
        val result = "ERROR #${counting}"
        callback?.onResult(result, 100)
        SystemClock.sleep(PROCESS_DURATION + TimeUnit.SECONDS.toMillis(2))
    }
}
