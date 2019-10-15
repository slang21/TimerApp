package com.spencer.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.lap_view.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var timer: Timer

    var activated = false

    var timerCreated = false

    var counter: Int = 0

    var diffCounter: Int = 0

    inner class TimerCount : TimerTask() {
        override fun run() {
            if (activated) {
                runOnUiThread {
                    counter += 1
                    diffCounter += 1
                    var seconds = (counter % 60).toString().padStart(2,'0')
                    var minutes = counter / 60
                    tvTime.text = "$minutes:$seconds"

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer = Timer()

        btnStart.setOnClickListener {
            if(!timerCreated) {
                timerCreated = true
                timer.schedule(TimerCount(), 1000, 1000)
            }
            activated = true
        }

        btnStop.setOnClickListener {
            activated = false
        }

        btnLap.setOnClickListener {
            addTime()
        }
    }

    private fun addTime() {
        var lapView = layoutInflater.inflate(
            R.layout.lap_view, null, false
        )

        lapView.tvLapValue.text = diffCounter.toString() + " seconds"

        lapTimes.addView(lapView, 0)
        diffCounter = 0
    }
}
