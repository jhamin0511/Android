package com.github.jhamin0511.android.async.thread.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.jhamin0511.android.async.thread.databinding.ActivityThreadBinding
import timber.log.Timber
import java.lang.Thread.State

class ThreadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThreadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBase()
    }

    // 실행 환경을 설정 및 해체는 무거운 작업으로 지양
    // 예제에서는 Thread의 State를 보기위한 용도로 사용
    private var thread: ExampleThread? = null

    private fun initBase() {
        binding.btInstance.setOnClickListener {
            Timber.d("Click Instance")
            if (thread == null) {
                thread = ExampleThread()
            } else {
                Timber.d("Already Instance")
            }
        }
        binding.btStart.setOnClickListener {
            Timber.d("Click Start")
            if (thread?.state == State.NEW) {
                thread?.start()
            } else {
                Timber.d("Not New Tread")
            }
        }
        binding.btStop.setOnClickListener {
            Timber.d("Click Stop")
            thread?.interrupt()
        }
        binding.btFinish.setOnClickListener {
            Timber.d("Click Finish")
            if (thread?.state != State.TERMINATED) {
                thread?.interrupt()
            }
            thread = null
        }
        binding.btState.setOnClickListener {
            Timber.d("Click State")
            Timber.i(thread?.state.toString())
        }
    }
}
