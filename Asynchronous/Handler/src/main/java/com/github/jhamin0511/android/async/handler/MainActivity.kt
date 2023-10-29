package com.github.jhamin0511.android.async.handler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.handler.databinding.ActivityMainBinding
import com.github.jhamin0511.android.async.handler.indicate.IndicateHandler
import com.github.jhamin0511.android.async.handler.indicate.IndicateThread
import com.github.jhamin0511.android.async.handler.looper.NoneLooperThread
import com.github.jhamin0511.android.async.handler.monitor.LogHandler

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

    private lateinit var monitorLogHandler: LogHandler
    private lateinit var noneLooperThread: NoneLooperThread
    private fun initNoneLooperThread() {
        monitorLogHandler = LogHandler(binding.logNoneLooper)

        binding.btCreateNoneLooperThread.setOnClickListener {
            noneLooperThread = NoneLooperThread(monitorLogHandler)
            noneLooperThread.start()
        }
    }

    companion object {
        private const val NONE_LOOPER_LOG = "NONE_LOOPER_LOG"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(NONE_LOOPER_LOG, binding.logNoneLooper.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val noneLooperLog = savedInstanceState.getString(NONE_LOOPER_LOG) ?: ""
        binding.logNoneLooper.text = noneLooperLog
    }

    override fun onDestroy() {
        super.onDestroy()

        looperThread.quit()
        indicateThread.exit()
    }
}
