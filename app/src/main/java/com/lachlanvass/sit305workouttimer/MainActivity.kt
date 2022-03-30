package com.lachlanvass.sit305workouttimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.TextClock

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start_button = findViewById<Button>(R.id.start_button)

        val timer = findViewById<Chronometer>(R.id.timer_display)
        timer.base = SystemClock.elapsedRealtime()

        start_button.setOnClickListener {

            timer.start()
        }
    }
}