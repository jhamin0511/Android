package com.github.jhamin0511.android.async.thread.basic

import android.os.SystemClock
import com.github.jhamin0511.android.async.thread.Counter
import timber.log.Timber

class StayThread(
    val listener: (Int) -> Unit
) : Thread("Stay ${Counter.getCount()}") {
    companion object {
        private const val MIN_DURATION = 100L
    }

    override fun run() {
        var count = 0
        var duration = 500L
        while (count < 100) {
            if (isInterrupted) {
                return
            }
            Timber.d("$name : $count")
            count++
            listener(count)
            SystemClock.sleep(duration)
            duration -= count
            if (duration < MIN_DURATION) {
                duration = MIN_DURATION
            }
        }
    }
}
