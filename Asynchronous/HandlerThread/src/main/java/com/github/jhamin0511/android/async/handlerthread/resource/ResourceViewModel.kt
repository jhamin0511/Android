package com.github.jhamin0511.android.async.handlerthread.resource

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ResourceViewModel(
    private val handlerThread: ResourceHandlerThread = Resource.handlerThread,
    private val preferences: CountingSharedPreferences = Preferences.countingSharedPreferences
) : ViewModel() {
    private val _result = MutableStateFlow(preferences.read().toString())
    val result = _result.asStateFlow()

    init {
        handlerThread.register(object : ResourceListener {
            override fun onResult(value: String) {
                _result.value = value
            }
        })
    }

    fun bindClickSendMinus() {
        handlerThread.sendMinus()
    }

    fun bindClickSendPlus() {
        handlerThread.sendPlus()
    }

    fun bindClickPostMinus() {
        handlerThread.postMinus()
    }

    fun bindClickPostPlus() {
        handlerThread.postPlus()
    }
}
