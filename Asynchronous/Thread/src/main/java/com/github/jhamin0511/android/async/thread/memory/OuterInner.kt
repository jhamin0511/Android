package com.github.jhamin0511.android.async.thread.memory

class OuterInner {
    fun sampleMethod() {
        val thread = InnerThread()
        thread.start()
    }

    private inner class InnerThread: Thread("Inner ${Counter.getCount()}") {
        override fun run() {
            val sampleObject = Any()
            sleep(Counter.time)
        }
    }
}
