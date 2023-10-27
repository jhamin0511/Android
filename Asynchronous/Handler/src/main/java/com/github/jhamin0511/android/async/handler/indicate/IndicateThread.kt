package com.github.jhamin0511.android.async.handler.indicate

import android.os.Handler
import android.os.Looper

class IndicateThread(
    private val handler: IndicateHandler
) : Thread() {
    private lateinit var indicateHandler: Handler
    private val logic = IndicateLogic()

    override fun run() {
        Looper.prepare()
        indicateHandler = Handler(Looper.myLooper() ?: Looper.getMainLooper())
        Looper.loop()
    }

    fun doWork() {
        indicateHandler.post {
            handler.startProgress()
            logic.startLogic {
                handler.sendProgress(it)
            }
            handler.finishProgress()
        }
    }

    fun exit() {
        indicateHandler.looper.quit()
    }
}
