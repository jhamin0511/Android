package com.github.jhamin0511.android.async.service

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.service.basic.BasicService
import com.github.jhamin0511.android.async.service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBasicService()
    }

    private fun initBasicService() {
        // startService가 최초 실행되면 OnCreate() -> onStartCommand() 순서로 호출 되며
        // 이미 startService가 실행된 경우며 onStartCommand()가 호출 된다.
        binding.btStartBasic.setOnClickListener {
            val intent = Intent(this, BasicService::class.java)
            startService(intent)
        }
        binding.btStopBasic.setOnClickListener {
            val intent = Intent(this, BasicService::class.java)
            stopService(intent)
        }
    }
}
