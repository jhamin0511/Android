package com.github.jhamin0511.android.async.thread.basic

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThreadStayViewModel : ViewModel() {
    private val _progress = MutableStateFlow(0)
    val progress = _progress.asStateFlow()

    private var thread: StayThread? = null
    private val listener: (Int) -> Unit = {
        _progress.value = it
    }

    fun start() {
        if (thread == null || thread?.state == Thread.State.TERMINATED) {
            thread = StayThread(listener)
            thread?.start()
        }
    }

    override fun onCleared() {
        super.onCleared()

        thread?.interrupt()
    }
}
