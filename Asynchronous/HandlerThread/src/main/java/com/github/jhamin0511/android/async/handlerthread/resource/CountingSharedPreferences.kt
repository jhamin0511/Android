package com.github.jhamin0511.android.async.handlerthread.resource

import android.content.Context
import androidx.core.content.edit
import com.github.jhamin0511.android.async.handlerthread.HandlerThreadApplication

object Preferences {
    val countingSharedPreferences = CountingSharedPreferences(HandlerThreadApplication.Context())
}

class CountingSharedPreferences(context: Context) {

    companion object {
        private const val COUNTING = "counting"
    }

    private val pref = context.getSharedPreferences(COUNTING, Context.MODE_PRIVATE)

    fun read(): Int {
        return pref.getInt(COUNTING, 0)
    }

    fun write(value: Int) {
        val counting = read() + value

        pref.edit(true) {
            putInt(COUNTING, counting)
        }
    }
}
