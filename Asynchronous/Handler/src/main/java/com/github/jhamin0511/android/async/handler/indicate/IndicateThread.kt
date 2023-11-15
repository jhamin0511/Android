package com.github.jhamin0511.android.async.handler.indicate

import android.os.Handler
import android.os.Looper

object IndicateThreadCounter {
    var counter = 1
}

class IndicateThread(
    private val handler: IndicateHandler
) : Thread("IndicateThread #${IndicateThreadCounter.counter++}") {
    private lateinit var indicateHandler: Handler
    private val logic = IndicateLogic()

    override fun run() {
        Looper.prepare()
        indicateHandler = Handler(Looper.myLooper() ?: Looper.getMainLooper())
        Looper.loop()
    }

    fun doWork(progress: Int = 0) {
        indicateHandler.post {
            handler.startProgress()
            logic.startLogic(progress) {
                handler.sendProgress(it)
            }
            handler.finishProgress()
        }
    }

    fun exit() {
        indicateHandler.looper.quit()
    }
}
