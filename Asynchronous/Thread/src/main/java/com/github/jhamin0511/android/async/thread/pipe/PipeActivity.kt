package com.github.jhamin0511.android.async.thread.pipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.async.thread.databinding.ActivityPipeBinding
import java.io.PipedReader
import java.io.PipedWriter

class PipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPipeBinding

    private lateinit var r: PipedReader
    private lateinit var w: PipedWriter
    private lateinit var workerThread: Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        r = PipedReader()
        w = PipedWriter()
        w.connect(r)

        binding.btMessageOne.setOnClickListener {
            w.write("Both producer and consumer may try to update the queue at the same time. This could lead to data loss or inconsistencies.")
        }
        binding.btMessageTwo.setOnClickListener {
            w.write("Producers might be slower than consumers. In such cases, the consumer would process elements fast and wait.")
        }
        binding.btMessageThree.setOnClickListener {
            w.write("In some cases, the consumer can be slower than the producer. This situation leads to a queue overflow issue.")
        }

        workerThread = Thread(ConsumeTask(r))
        workerThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        workerThread.interrupt()
        r.close()
        w.close()
    }
}
