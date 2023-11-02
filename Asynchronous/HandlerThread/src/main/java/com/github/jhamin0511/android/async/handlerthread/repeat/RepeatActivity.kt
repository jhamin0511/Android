package com.github.jhamin0511.android.async.handlerthread.repeat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.jhamin0511.android.async.handlerthread.R
import com.github.jhamin0511.android.async.handlerthread.databinding.ActivityRepeatBinding

class RepeatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepeatBinding
    private val viewModel: RepeatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repeat)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }
}
