package com.github.jhamin0511.android.async.handlerthread.chain

object NetworkThread {
    private val handlerThread = NetworkHandlerThread()

    init {
        handlerThread.start()
    }

    fun get() = handlerThread
}
