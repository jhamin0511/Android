package com.github.jhamin0511.android.async.handler.monitor

class MonitorLogText {

    fun combineMessage(originalMessage: CharSequence, newMessage: CharSequence): String {
        return buildString {
            append(newMessage)
            if (originalMessage.isNotEmpty()) {
                appendLine()
                append(originalMessage)
            }
        }
    }
}
