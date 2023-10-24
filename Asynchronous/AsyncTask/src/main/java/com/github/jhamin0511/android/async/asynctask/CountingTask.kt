package com.github.jhamin0511.android.async.asynctask

import android.os.AsyncTask
import android.widget.TextView

class CountingTask(
    val textView: TextView
) : AsyncTask<Unit, Int, Unit>() {
    private var count = 0

    override fun doInBackground(vararg params: Unit?) {
        println("[${this.address()}] doInBackground($params)")

        while (count < 50) {
            Thread.sleep(1000)
            publishProgress(++count)
        }
    }

    override fun onPreExecute() {
        println("[#${textView.address()}] reference()")
        println("[${this.address()}] onPreExecute()")
    }

    override fun onPostExecute(result: Unit?) {
        println("[${this.address()}] onPostExecute($result)")
    }

    override fun onProgressUpdate(vararg values: Int?) {
        val value = values[0].toString()
        println("[${this.address()}] onProgressUpdate($value)")

        textView.text = value
    }

    override fun onCancelled(result: Unit?) {
        println("[${this.address()}] onCancelled($result)")
    }

    override fun onCancelled() {
        println("[${this.address()}] onCancelled()")
    }
}

@OptIn(ExperimentalStdlibApi::class)
private fun Any.address(): String {
    val name = this::class.java.simpleName
    val address = System.identityHashCode(this).toHexString()

    return "$name : #$address"
}
