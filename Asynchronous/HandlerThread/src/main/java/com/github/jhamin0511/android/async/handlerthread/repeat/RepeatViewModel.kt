package com.github.jhamin0511.android.async.handlerthread.repeat

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RepeatViewModel(
    private val handlerThread: RepeatHandlerThread = Repeat.handlerThread
) : ViewModel() {
    private val _result = MutableStateFlow("")
    val result = _result.asStateFlow()
    private val listener = object : RepeatListener {
        override fun onResult(result: String) {
            _result.value = result
        }
    }

    init {
        handlerThread.register(listener)
    }

    fun bindClickSend() {
        handlerThread.send()
    }

    fun bindClickPost() {
        handlerThread.post()
    }

    override fun onCleared() {
        handlerThread.unregister(listener)
    }
}
