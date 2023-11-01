package com.github.jhamin0511.android.async.thread.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        binding.btException.setOnClickListener {
            Timber.d("Click Exception")
            thread?.exception()
            thread = null
        }
    }

    /*
    /**
     * 설정 변경이 일어나기 전에 플랫폼에서 호출
     *
     * 새로운 Activity 객체에 전달하고자 하는 객체를 반환
     *
     * ViewModel 사용 권장함
     * */
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return super.onRetainCustomNonConfigurationInstance()
    }
    */

    /*
    /**
     * 설정 변경이 이루어진 후에 호출
     *
     * onRetainNonConfigurationInstance()에서 반환된 객체를 가져오기 위해
     *
     * 새로운 Acitivity 객체에서 호출
     * */
    val instance = lastNonConfigurationInstance
    val customInstance = lastCustomNonConfigurationInstance
    */
}
