package com.github.jhamin0511.android.async.thread

import com.github.jhamin0511.android.async.thread.pipe.ConsumeTask
import java.io.PipedReader
import java.io.PipedWriter

fun main() {
    val r: PipedReader = PipedReader()
    val w: PipedWriter = PipedWriter()
    w.connect(r)

    w.write("Hello")

    Thread(ConsumeTask(r)).start()

}
