package com.github.jhamin0511.android.async.handler.indicate

import kotlin.random.Random

class IndicateLogic {

    fun startLogic(plusTime: Long = 3000, listener: (Int) -> Unit) {
        val time = Random.nextDouble(2000.0) + plusTime
        var indicate = 0
        while (indicate < time) {
            val div = indicate / time
            val progress = (div * 100).toInt()
            listener(progress)
            Thread.sleep(indicate.toLong())
            indicate += 300
        }
    }
}
