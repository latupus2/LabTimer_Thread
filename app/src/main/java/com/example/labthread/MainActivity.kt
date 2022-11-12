package com.example.labthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.Timer)

        var thread: Thread? = null
        var i = 10
        var flag = true

        val buttonStart = findViewById<Button>(R.id.Start)
        val buttonStop = findViewById<Button>(R.id.Stop)

        buttonStart.setOnClickListener {
            if(thread == null) {
                thread = thread {
                    while (true) {
                        try {
                            Thread.sleep(1000)
                        } catch (e: InterruptedException) {
                            // We've been interrupted: no more messages.
                            return@thread
                        }
                        if (i <= 0) {
                            flag = false
                        } else if (i >= 10) {
                            flag = true
                        }
                        if (flag) {
                            i--
                        } else {
                            i++
                        }
                        runOnUiThread {
                            textView.text = i.toString()
                        }
                    }
                }
            }
        }

        buttonStop.setOnClickListener {
            thread?.interrupt()
            thread = null
        }
    }

}