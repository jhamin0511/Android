package com.github.jhamin0511.android.async.handler

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.handler.databinding.ActivityMainBinding
import com.github.jhamin0511.android.async.handler.indicate.IndicateHandler
import com.github.jhamin0511.android.async.handler.indicate.IndicateThread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var looperThread: LooperThread
    private lateinit var indicateHandler: IndicateHandler
    private lateinit var indicateThread: IndicateThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        looperThread = LooperThread()
        looperThread.start()

        indicateHandler =
            IndicateHandler(binding.pbIndicate, binding.tvIndicate, binding.btStartIndicate)
        indicateThread = IndicateThread(indicateHandler)
        indicateThread.start()

        binding.btCreateHandler.setOnClickListener {
            Thread {
                // java.lang.RuntimeException: Can't create handler inside thread
                // Thread[Thread-3,5,main] that has not called Looper.prepare()
                val handler = Handler()
            }.start()
        }
        var count = 0
        binding.btSendMessage.setOnClickListener {
            looperThread.sendMessage(count++)
        }
        binding.btSendMessageDelayed.setOnClickListener {
            looperThread.sendMessageDelayed(count++)
        }
        binding.btPost.setOnClickListener {
            looperThread.post(count++)
        }
        binding.btPostDelayed.setOnClickListener {
            looperThread.postDelayed(count++)
        }
        binding.btIdle.setOnClickListener {
            looperThread.addIdleHandler(count++)
        }
        binding.btStartIndicate.setOnClickListener {
            indicateThread.doWork()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        looperThread.quit()
        indicateThread.exit()
    }
}
