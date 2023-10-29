package com.github.jhamin0511.android.async.handler.looper

import android.os.Handler

class NoneLooperThread(
    private val noneLooperHandler: NoneLooperHandler
) : Thread() {
    private lateinit var handler: Handler
    override fun run() {
        try {
            handler = Handler()
        } catch (e: RuntimeException) {
            noneLooperHandler.sendException(e)
        }
    }

    fun exit() {
        try {
            handler.looper.quit()
        } catch (e: UninitializedPropertyAccessException) {
            noneLooperHandler.sendException(e)
        }
    }
}
