package com.github.jhamin0511.android.async.handler.looper

class TextMonitorLogger {

    fun combineMessage(originalMessage: CharSequence, newMessage: CharSequence): String {
        return buildString {
            if (originalMessage.isNotEmpty()) {
                append(originalMessage)
                appendLine()
            }
            append(newMessage)
        }
    }
}
