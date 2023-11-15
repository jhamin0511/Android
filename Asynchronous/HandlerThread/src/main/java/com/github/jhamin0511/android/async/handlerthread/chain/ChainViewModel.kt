package com.github.jhamin0511.android.async.handlerthread.chain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChainViewModel(
    private val network: NetworkHandlerThread = NetworkThread.get()
) : ViewModel() {
    private val _result = MutableStateFlow("")
    val result = _result.asStateFlow()
    private val _progress = MutableStateFlow(0)
    val progress = _progress.asStateFlow()

    init {
        network.registerCallback(object : NetworkCallback {
            override fun onResult(result: String, progress: Int) {
                _result.value = result
                _progress.value = progress
            }
        })
    }

    fun bindClickCall() {
        network.call()
    }
}
