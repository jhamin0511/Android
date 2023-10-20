package com.github.jhamin0511.android.core.util

import android.view.View

fun View.setVisible(value: Boolean?) {
    visibility = if (value == true) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.setVisible(value: String?) {
    setVisible(!value.isNullOrEmpty())
}
