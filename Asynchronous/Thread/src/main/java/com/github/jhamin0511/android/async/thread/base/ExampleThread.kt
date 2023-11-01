package com.github.jhamin0511.android.async.thread.base

import android.os.SystemClock
import com.github.jhamin0511.android.async.thread.Counter
import timber.log.Timber

class ExampleThread : Thread("ExampleThread ${Counter.getCount()}") {
    private var count = 0

    override fun run() {
        while (true) {
            if (isInterrupted) {
                return
            }
            Timber.i("$name Task #${count++}")
            SystemClock.sleep(1000)
        }
    }
}
