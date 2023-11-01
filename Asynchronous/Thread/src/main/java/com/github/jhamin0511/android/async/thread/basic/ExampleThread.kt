package com.github.jhamin0511.android.async.thread.basic

import android.os.SystemClock
import com.github.jhamin0511.android.async.thread.Counter
import timber.log.Timber

class ExampleThread : Thread("Example ${Counter.getCount()}") {
    private var count = 0
    private var isException = false

    init {
        setDefaultUncaughtExceptionHandler { t, e ->
            Timber.d(e, t.name)
        }
    }

    override fun run() {
        while (true) {
            if (isInterrupted) {
                return
            }
            if (isException) {
                throw IllegalStateException("익셉션 발생 상태.")
            }
            Timber.i("$name Task #${count++}")
            SystemClock.sleep(1000)
        }
    }

    fun exception() {
        isException = true
    }
}
