package com.github.jhamin0511.android.async.handler.indicate

class IndicateLogic {

    companion object {
        private const val SLEEP_TIME = 10L
        private const val INCREASE_PERCENT = 7
    }

    fun startLogic(progress: Int = 0, listener: (Int) -> Unit) {
        var localProgress: Int = progress

        do {
            listener(localProgress)
            Thread.sleep(localProgress.times(SLEEP_TIME))
            localProgress += INCREASE_PERCENT
        } while (localProgress < 100)
    }
}
