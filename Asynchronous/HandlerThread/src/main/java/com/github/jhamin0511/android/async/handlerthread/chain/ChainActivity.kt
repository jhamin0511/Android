package com.github.jhamin0511.android.async.handlerthread.chain

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.jhamin0511.android.async.handlerthread.R
import com.github.jhamin0511.android.async.handlerthread.databinding.ActivityChainBinding

class ChainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChainBinding
    private val viewModel: ChainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chain)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }
}
