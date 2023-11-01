package com.github.jhamin0511.android.async.thread.memory

import com.github.jhamin0511.android.async.thread.Counter

class OuterStaticInner {
    fun sampleMethod() {
        val thread = StaticInnerThread()
        thread.start()
    }

    companion object {
        private class StaticInnerThread : Thread("StaticInner ${Counter.getCount()}") {
            override fun run() {
                val any = Any()
                sleep(Counter.time)
            }
        }
    }
}
