package com.github.jhamin0511.android.core.design

import android.content.res.Resources

fun Resources.toDp(value: Int) = (value * displayMetrics.density).toInt()
