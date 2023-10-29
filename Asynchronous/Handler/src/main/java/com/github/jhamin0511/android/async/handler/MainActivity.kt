package com.github.jhamin0511.android.async.handler

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.handler.databinding.ActivityMainBinding
import com.github.jhamin0511.android.async.handler.indicate.IndicateHandler
import com.github.jhamin0511.android.async.handler.indicate.IndicateThread
import com.github.jhamin0511.android.async.handler.looper.NoneLooperHandler
import com.github.jhamin0511.android.async.handler.looper.NoneLooperThread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var looperThread: LooperThread
    private lateinit var indicateHandler: IndicateHandler
    private lateinit var indicateThread: IndicateThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNoneLooperThread()

        looperThread = LooperThread()
        looperThread.start()

        indicateHandler =
            IndicateHandler(binding.pbIndicate, binding.tvIndicate, binding.btStartIndicate)
        indicateThread = IndicateThread(indicateHandler)
        indicateThread.start()

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

    private lateinit var noneLooperHandler: NoneLooperHandler
    private lateinit var noneLooperThread: NoneLooperThread
    private fun initNoneLooperThread() {
        binding.tvMonitorNoneLooper.movementMethod = ScrollingMovementMethod.getInstance()
        noneLooperHandler = NoneLooperHandler(binding.tvMonitorNoneLooper)

        binding.btCreateNoneLooperThread.setOnClickListener {
            noneLooperThread = NoneLooperThread(noneLooperHandler)
            noneLooperThread.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        noneLooperThread.exit()
        looperThread.quit()
        indicateThread.exit()
    }
}
