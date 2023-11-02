package com.github.jhamin0511.android.async.handlerthread.resource

object Resource {
    val handlerThread = ResourceHandlerThread()

    init {
        handlerThread.start()
    }
}
