package com.github.jhamin0511.android.async.thread.memory.prevention

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.thread.databinding.ActivityMemoryPreventionBinding
import com.github.jhamin0511.android.async.thread.memory.prevention.staticinner.JOuter
import com.github.jhamin0511.android.async.thread.memory.prevention.staticinner.KOuter

class MemoryPreventionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoryPreventionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoryPreventionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initStaticInner()
    }

    private val jOuter = JOuter()
    private val kOuter = KOuter()

    private fun initStaticInner() {
        binding.btRunJavaStaticInner.setOnClickListener {
            jOuter.sampleMethod()
        }
        binding.btRunKotlinNested.setOnClickListener {
            kOuter.sampleMethod()
        }
    }
}
