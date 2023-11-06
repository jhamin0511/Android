package com.github.jhamin0511.android.async.executor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.executor.databinding.ActivityMainBinding
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNewFixedThreadPool()
    }

    private val newFixedThreadPoolCounting = AtomicInteger(0)

    private fun initNewFixedThreadPool() {
        binding.btExecuteNewFixedThreadPool.setOnClickListener {
            ExecutorSingleton.newFixedThreadPool.execute {
                val count = newFixedThreadPoolCounting.get() + 1
                newFixedThreadPoolCounting.set(count)
                TimeUnit.SECONDS.sleep(5)
                println("[${Thread.currentThread().name}] Counting : $count")
            }
        }
        binding.btSubmitNewFixedThreadPool.setOnClickListener {
            val future = ExecutorSingleton.newFixedThreadPool.submit(
                Callable {
                    val count = newFixedThreadPoolCounting.get() + 1
                    newFixedThreadPoolCounting.set(count)
                    TimeUnit.SECONDS.sleep(5)
                    println("${Thread.currentThread().name} Counting : $count")
                    count
                }
            )
            val result = future.get()
            println("${Thread.currentThread().name}[Result] : $result")
        }
    }
}
