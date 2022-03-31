package com.lachlanvass.sit305workouttimer

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var timeMillis: Long = 0L
    private var taskName: String = ""

    private val timeMillisKey = "TIME_MILLIS"
    private val taskNameKey = "TASK_NAME"

    // Views
    val startButton = findViewById<Button>(R.id.start_button)
    val pauseButton = findViewById<Button>(R.id.pause_button)
    val stopButton = findViewById<Button>(R.id.stop_button)
    val timer = findViewById<Chronometer>(R.id.timer_display)

    val workoutSummary = findViewById<TextView>(R.id.workout_summary)
    val taskInput = findViewById<EditText>(R.id.task_name_input)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer.format = "Time Running - %s"
        timer.base = SystemClock.elapsedRealtime()

        startButton.setOnClickListener {

            timer.base = SystemClock.elapsedRealtime() + timeMillis;
            timer.start()
        }

        pauseButton.setOnClickListener {

            timeMillis = timer.base - SystemClock.elapsedRealtime();
            timer.stop()
            println("Pause button")
        }

        stopButton.setOnClickListener {

            timer.stop()

            timeMillis = SystemClock.elapsedRealtime() - timer.base

            val minutes = timeMillis / 1000 / 60
            val seconds = timeMillis / 1000 % 60

            with (taskInput.text) {
                taskName = when {
                    isEmpty() -> "NO_TASK_NAME"
                    else -> taskInput.text.toString()
                }
            }

            workoutSummary.text = "You spent $minutes:$seconds on ${taskName}"
            timer.base = SystemClock.elapsedRealtime();
            timeMillis = 0
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putLong(timeMillisKey, timeMillis)
        outState.putString(taskNameKey, taskName)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        timeMillis = savedInstanceState.getLong("TIME_MILLIS")
        taskName = savedInstanceState.getString("TASK_NAME")!!

    }
}