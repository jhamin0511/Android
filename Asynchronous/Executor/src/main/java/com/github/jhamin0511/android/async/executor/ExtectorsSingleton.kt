package com.github.jhamin0511.android.async.executor

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object ExecutorSingleton {
    val newFixedThreadPool: ExecutorService = Executors.newFixedThreadPool(10)
}
