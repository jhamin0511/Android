package com.github.jhamin0511.android.async.handler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.handler.databinding.ActivityMainBinding
import com.github.jhamin0511.android.async.handler.indicate.IndicateHandler
import com.github.jhamin0511.android.async.handler.indicate.IndicateThread
import com.github.jhamin0511.android.async.handler.looper.LooperThread
import com.github.jhamin0511.android.async.handler.looper.NoneLooperThread
import com.github.jhamin0511.android.async.handler.monitor.LogHandler
import com.github.jhamin0511.android.async.handler.monitor.LogTextView
import com.github.jhamin0511.android.async.handler.snapshot.SnapshotThread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNoneLooperThread()
        initLooperThread()
        initIndicate()
        initSnapshot()
    }

    private lateinit var noneLooperLogHandler: LogHandler
    private lateinit var noneLooperThread: NoneLooperThread
    private fun initNoneLooperThread() {
        noneLooperLogHandler = LogHandler(binding.logNoneLooper)

        binding.btCreateNoneLooperThread.setOnClickListener {
            noneLooperThread = NoneLooperThread(noneLooperLogHandler)
            noneLooperThread.start()
        }
    }

    private lateinit var looperLogHandler: LogHandler
    private lateinit var looperThread: LooperThread

    private fun initLooperThread() {
        looperLogHandler = LogHandler(binding.logLooper)
        looperThread = LooperThread(looperLogHandler)
        looperThread.start()


        var count = 0
        binding.btSend.setOnClickListener {
            looperThread.send(count++)
        }
        binding.btSendDelayed.setOnClickListener {
            looperThread.sendDelayed(count++)
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
    }

    private lateinit var indicateHandler: IndicateHandler
    private lateinit var indicateThread: IndicateThread
    private fun initIndicate() {
        indicateHandler =
            IndicateHandler(binding.pbIndicate, binding.tvIndicate, binding.btStartIndicate)
        indicateThread = IndicateThread(indicateHandler)
        indicateThread.start()

        binding.btStartIndicate.setOnClickListener {
            indicateThread.doWork()
        }
    }

    private fun initSnapshot() {
        val thread = SnapshotThread()
        thread.start()

        binding.btSnapshot.setOnClickListener {
            thread.snapshotMessageQueue()
        }
    }

    companion object {
        private const val NONE_LOOPER_LOG = "NONE_LOOPER_LOG"
        private const val LOOPER_LOG = "LOOPER_LOG"
        private const val INDICATE = "INDICATE"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(NONE_LOOPER_LOG, binding.logNoneLooper.text.toString())
        outState.putString(LOOPER_LOG, binding.logLooper.text.toString())
//        val progress = binding.pbIndicate.progress
//        outState.putInt(INDICATE, progress)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        loadLog(savedInstanceState, binding.logNoneLooper, NONE_LOOPER_LOG)
        loadLog(savedInstanceState, binding.logLooper, LOOPER_LOG)
//        val progress = savedInstanceState.getInt(INDICATE)
//        indicateThread.doWork(progress)
    }

    private fun loadLog(bundle: Bundle, textView: LogTextView, key: String) {
        textView.text = bundle.getString(key) ?: ""
    }

    override fun onDestroy() {
        super.onDestroy()

        looperThread.quit()
//        indicateThread.exit()
    }
}
