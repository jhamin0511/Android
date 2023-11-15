package com.github.jhamin0511.android.async.handlerthread.repeat

object Repeat {
    val handlerThread = RepeatHandlerThread()

    init {
        handlerThread.start()
    }
}
