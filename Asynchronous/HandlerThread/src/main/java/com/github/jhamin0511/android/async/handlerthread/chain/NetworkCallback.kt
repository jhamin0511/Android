package com.github.jhamin0511.android.async.handlerthread.chain

interface NetworkCallback {
    fun onResult(result: String, progress: Int)
}
