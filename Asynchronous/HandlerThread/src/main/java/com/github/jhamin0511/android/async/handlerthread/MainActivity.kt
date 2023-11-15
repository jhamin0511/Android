package com.github.jhamin0511.android.async.handlerthread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.jhamin0511.android.async.handlerthread.chain.ChainActivity
import com.github.jhamin0511.android.async.handlerthread.databinding.ActivityMainBinding
import com.github.jhamin0511.android.async.handlerthread.repeat.RepeatActivity
import com.github.jhamin0511.android.async.handlerthread.resource.ResourceActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btStartRepeat.setOnClickListener {
            startActivity<RepeatActivity>()
        }
        binding.btStartResource.setOnClickListener {
            startActivity<ResourceActivity>()
        }
        binding.btStartChain.setOnClickListener {
            startActivity<ChainActivity>()
        }
    }
}
