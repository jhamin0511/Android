package com.github.jhamin0511.android.async.handler.looper

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TextMonitorLoggerTest {
    private val logger = TextMonitorLogger()

    @Test
    fun combineMessageWithEmptyOriginal() {
        // Given
        val original = ""
        val newMessage = "newMessage"
        // When
        val result = logger.combineMessage(original, newMessage)
        // Then
        assertThat(result).isEqualTo(newMessage)
    }

    @Test
    fun combineMessage() {
        // Given
        val original = "original"
        val newMessage = "newMessage"
        // When
        val result = logger.combineMessage(original, newMessage)
        // Then
        val expected = "${original}\n$newMessage"
        assertThat(result).isEqualTo(expected)
    }
}
