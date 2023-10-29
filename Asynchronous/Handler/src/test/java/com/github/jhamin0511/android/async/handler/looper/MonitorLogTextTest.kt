package com.github.jhamin0511.android.async.handler.looper

import com.github.jhamin0511.android.async.handler.monitor.MonitorLogText
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MonitorLogTextTest {
    private val logText = MonitorLogText()

    @Test
    fun combineMessageWithEmptyOriginal() {
        // Given
        val original = ""
        val newMessage = "newMessage"
        // When
        val result = logText.combineMessage(original, newMessage)
        // Then
        assertThat(result).isEqualTo(newMessage)
    }

    @Test
    fun combineMessage() {
        // Given
        val original = "original"
        val newMessage = "newMessage"
        // When
        val result = logText.combineMessage(original, newMessage)
        // Then
        val expected = "${newMessage}\n$original"
        assertThat(result).isEqualTo(expected)
    }
}
