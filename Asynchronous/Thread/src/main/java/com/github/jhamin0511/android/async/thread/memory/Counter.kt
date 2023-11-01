package com.github.jhamin0511.android.async.thread.memory

import java.util.concurrent.TimeUnit

object Counter {
    val time = TimeUnit.MINUTES.toMillis(1)
    private var count = 0

    fun getCount() = "#${count++}"
}
