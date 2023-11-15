package com.github.jhamin0511.android.async.handlerthread.resource

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.jhamin0511.android.async.handlerthread.R
import com.github.jhamin0511.android.async.handlerthread.databinding.ActivityResourceBinding

class ResourceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResourceBinding
    private val viewModel: ResourceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_resource)
        binding.lifecycleOwner = this
        binding.vm = viewModel
    }
}
