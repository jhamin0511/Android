package com.github.jhamin0511.android.async.executor

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object ExecutorSingleton {
    // 스레드 풀 10개 고정
    val newFixedThreadPool: ExecutorService = Executors.newFixedThreadPool(10)
    // 스레드 풀은 실행할 태스크 수와 함께 늘어나고 줄어듬
    val newCachedThreadPool: ExecutorService = Executors.newCachedThreadPool()
}
