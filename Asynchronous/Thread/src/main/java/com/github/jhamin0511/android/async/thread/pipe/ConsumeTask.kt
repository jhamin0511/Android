package com.github.jhamin0511.android.async.thread.pipe

import java.io.IOException
import java.io.PipedReader

class ConsumeTask(
    private val reader: PipedReader
) : Runnable {
    override fun run() {
        while (!Thread.currentThread().isInterrupted) {
            try {
                var i = 0
                while (reader.read().also { i = it } != -1) {
                    print(i.toChar())
                    Thread.sleep(1000)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
