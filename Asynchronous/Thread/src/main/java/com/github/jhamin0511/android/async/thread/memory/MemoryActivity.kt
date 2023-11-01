package com.github.jhamin0511.android.async.thread.memory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.thread.databinding.ActivityMemoryBinding
import java.lang.Thread.sleep

class MemoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initThread()
    }

    private fun initThread() {
        binding.btRunAnonymousThread.setOnClickListener {
            object : Thread("Anonymous ${Counter.getCount()}") {
                override fun run() {
                    val sampleObject = Any()
                    sleep(Counter.time)
                }
            }.start()
        }
        binding.btRunRunnableThread.setOnClickListener {
            Thread(
                {
                    val sampleObject = Any()
                    sleep(Counter.time)
                }, "Runnable ${Counter.getCount()}"
            ).start()
        }
        binding.btRunInner.setOnClickListener {
            val outerInner = OuterInner()
            outerInner.sampleMethod()
        }
        binding.btRunStaticInner.setOnClickListener {
            val outerStaticInner = OuterStaticInner()
            outerStaticInner.sampleMethod()
        }
        val handlerDataThread = HandlerDataThread()
        handlerDataThread.start()
        binding.btSendData.setOnClickListener {
            handlerDataThread.doSend()
        }
        val handlerTaskThread = HandlerTaskThread()
        handlerTaskThread.start()
        binding.btPostTask.setOnClickListener {
            handlerTaskThread.doPost()
        }
    }
}
