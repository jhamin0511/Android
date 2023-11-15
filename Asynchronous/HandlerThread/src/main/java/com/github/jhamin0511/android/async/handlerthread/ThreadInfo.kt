package com.github.jhamin0511.android.async.handlerthread

object ThreadInfo {
    private var count = 0

    fun counting() = "#${count++}"
}
