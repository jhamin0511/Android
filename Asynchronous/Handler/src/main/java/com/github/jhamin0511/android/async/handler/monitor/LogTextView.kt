package com.github.jhamin0511.android.async.handler.monitor

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding
import com.github.jhamin0511.android.core.design.R
import com.github.jhamin0511.android.core.design.toDp

class LogTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    private val logger = MonitorLogText()

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        setBackgroundResource(R.drawable.bg_example_monitor)
        val padding = resources.getDimension(R.dimen.space_widget_large).toInt()
        setPadding(padding)
        setTextColor(resources.getColor(android.R.color.white, null))
        isVerticalScrollBarEnabled = true
        maxHeight = resources.toDp(100)
        minHeight = resources.toDp(100)

        movementMethod = ScrollingMovementMethod.getInstance()
    }

    fun setLog(value: CharSequence) {
        val original = text
        text = logger.combineMessage(original, value)
    }
}
