package com.github.jhamin0511.android.async.handler.indicate

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class IndicateLogicTest {
    private val logic = IndicateLogic()

    @Test
    fun startLogicTest() {
        logic.startLogic {
            println(it)
            assertThat(it <= 100).isTrue()
        }
    }
}
