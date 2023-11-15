package com.github.jhamin0511.android.async.executor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.executor.databinding.ActivityMainBinding
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    companion object {
        private const val SLEEP_TIME = 5L
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNewFixedThreadPool()
    }

    private var count = 0

    private fun initNewFixedThreadPool() {
        binding.btExecuteNewFixedThreadPool.setOnClickListener {
            ExecutorSingleton.newFixedThreadPool.execute {
                TimeUnit.SECONDS.sleep(SLEEP_TIME)
                println("[${Thread.currentThread().name}] Counting : ${count++}")
            }
        }
        binding.btSubmitNewFixedThreadPool.setOnClickListener {
            val future = ExecutorSingleton.newFixedThreadPool.submit(
                Callable {
                    TimeUnit.SECONDS.sleep(SLEEP_TIME)
                    println("[${Thread.currentThread().name}] Counting : ${count++}")
                    count
                }
            )
            val result = future.get()
            println("[${Thread.currentThread().name}][Result] : $result")
        }
        binding.btExecuteNewCachedThreadPool.setOnClickListener {
            ExecutorSingleton.newCachedThreadPool.execute {
                TimeUnit.SECONDS.sleep(SLEEP_TIME)
                println("[${Thread.currentThread().name}] Counting : ${count++}")
            }
        }
        binding.btSubmitNewCachedThreadPool.setOnClickListener {
            val future = ExecutorSingleton.newCachedThreadPool.submit(
                Callable {
                    TimeUnit.SECONDS.sleep(SLEEP_TIME)
                    println("[${Thread.currentThread().name}] Counting : ${count++}")
                    count
                }
            )
            val result = future.get()
            println("[${Thread.currentThread().name}][Result] : $result")
        }
        binding.btExecuteNewSingledThreadPool.setOnClickListener {
            ExecutorSingleton.newSingleThreadPool.execute {
                TimeUnit.SECONDS.sleep(SLEEP_TIME)
                println("[${Thread.currentThread().name}] Counting : ${count++}")
            }
        }
        binding.btSubmitNewCachedThreadPool.setOnClickListener {
            val future = ExecutorSingleton.newSingleThreadPool.submit(
                Callable {
                    TimeUnit.SECONDS.sleep(SLEEP_TIME)
                    println("[${Thread.currentThread().name}] Counting : ${count++}")
                    count
                }
            )
            val result = future.get()
            println("[${Thread.currentThread().name}][Result] : $result")
        }
    }
}
