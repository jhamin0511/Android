package com.github.jhamin0511.android.async.thread

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.thread.base.ThreadActivity
import com.github.jhamin0511.android.async.thread.databinding.ActivityMainBinding
import com.github.jhamin0511.android.async.thread.memory.MemoryActivity
import com.github.jhamin0511.android.async.thread.memory.prevention.MemoryPreventionActivity
import com.github.jhamin0511.android.async.thread.pipe.PipeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStartThread.setOnClickListener {
            startActivity<ThreadActivity>()
        }
        binding.btStartPipe.setOnClickListener {
            startActivity<PipeActivity>()
        }
        binding.btStartMemory.setOnClickListener {
            startActivity<MemoryActivity>()
        }
        binding.btStartMemoryPrevention.setOnClickListener {
            startActivity<MemoryPreventionActivity>()
        }
    }
}

inline fun <reified T : Activity> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}
