package com.github.jhamin0511.android.async.asynctask

import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val view = findViewById<TextView>(R.id.tv_count)
        CountingTask(view).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }
}
