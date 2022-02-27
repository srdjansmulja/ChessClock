package com.example.chessclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class TimerActivity : AppCompatActivity() {



    var START_MILLI_SECONDS = 60000L

    lateinit var countdown_timer_one: CountDownTimer
    lateinit var countdown_timer_two: CountDownTimer
    var timerOneisRunning: Boolean = false
    var timerTwoIsRunning: Boolean = false
    var whitePlays: Boolean = false
    var blackPlays: Boolean = false
    var gameIsPaused: Boolean = false
    var time_in_milli_seconds = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val timerOne: TextView = findViewById(R.id.timerOne)
        val timerTwo: TextView = findViewById(R.id.timerTwo)
        val button: Button = findViewById(R.id.StartStopButton)
        button.setBackgroundResource(R.drawable.ic_start)

        val playerOneTime:String = intent.getStringExtra("playerOneTime").toString()
        val playerTwoTime:String = intent.getStringExtra("playerTwoTime").toString()
        val playerOneName:String = intent.getStringExtra("playerOneName").toString()
        val playerTwoName:String = intent.getStringExtra("playerTwoName").toString()
        val playerOneNameDisplayed: TextView = findViewById(R.id.playerOneName)
        val playerTwoNameDisplayed: TextView = findViewById(R.id.playerTwoName)
        timerOne.text = playerOneTime + ":00"
        timerTwo.text = playerTwoTime + ":00"
        playerOneNameDisplayed.text = playerOneName
        playerTwoNameDisplayed.text = playerTwoName

        timerOne.setOnClickListener {
            if (timerOneisRunning) {
                pauseTimerOne()
                whitePlays = false
                blackPlays = true
                var time = timerTwo.text.toString().split(":").toMutableList()
                time_in_milli_seconds = (time[0].toLong() *60000L + time[1].toLong() * 1000L)
                startTimerTwo(time_in_milli_seconds)
            }
        }

        timerTwo.setOnClickListener {
            if (timerTwoIsRunning) {
                pauseTimerTwo()
                blackPlays = false
                whitePlays = true

                var time = timerOne.text.toString().split(":").toMutableList()
                time_in_milli_seconds = (time[0].toLong() *60000L + time[1].toLong() * 1000L)
                startTimerOne(time_in_milli_seconds)
            }
        }

        button.setOnClickListener {
            //start the game
            when{
                !whitePlays && !blackPlays ->
                        {
                            var time = timerOne.text.toString().split(":").toMutableList()
                            time_in_milli_seconds = (time[0].toLong() *60000L + time[1].toLong() * 1000L)
                            startTimerOne(time_in_milli_seconds)
                            whitePlays = true
                            button.setBackgroundResource(R.drawable.ic_pause)
                        }
                whitePlays && !gameIsPaused ->
                {
                    pauseTimerOne()
                    gameIsPaused = true
                    button.setBackgroundResource(R.drawable.ic_start)
                }
                blackPlays && !gameIsPaused ->
                {
                    pauseTimerTwo()
                    gameIsPaused = true
                    button.setBackgroundResource(R.drawable.ic_start)
                }
                whitePlays && gameIsPaused ->
                {
                    var time = timerOne.text.toString().split(":").toMutableList()
                    time_in_milli_seconds = (time[0].toLong() *60000L + time[1].toLong() * 1000L)
                    startTimerOne(time_in_milli_seconds)
                    gameIsPaused = false
                    button.setBackgroundResource(R.drawable.ic_pause)

                }
                blackPlays && gameIsPaused ->
                {
                    var time = timerTwo.text.toString().split(":").toMutableList()
                    time_in_milli_seconds = (time[0].toLong() *60000L + time[1].toLong() * 1000L)
                    startTimerTwo(time_in_milli_seconds)
                    gameIsPaused = false
                    button.setBackgroundResource(R.drawable.ic_pause)

                }
            }
        }
    }

    private fun pauseTimerOne() {
        countdown_timer_one.cancel()
        timerOneisRunning = false

    }
    private fun pauseTimerTwo() {
        countdown_timer_two.cancel()
        timerTwoIsRunning = false

    }

    private fun startTimerOne(time_in_seconds: Long) {
        countdown_timer_one = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                loadConfeti()
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUIOne()
            }
        }
        countdown_timer_one.start()

        timerOneisRunning = true
    }

    private fun startTimerTwo(time_in_seconds: Long) {
        countdown_timer_two = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                loadConfeti()
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUITwo()
            }
        }
        countdown_timer_two.start()

        timerTwoIsRunning = true
    }



    private fun resetTimer() {
        time_in_milli_seconds = START_MILLI_SECONDS
        updateTextUIOne()
    }

    private fun updateTextUIOne() {
        val timerOne: TextView = findViewById(R.id.timerOne)
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        timerOne.text = "$minute:$seconds"
    }

    private fun updateTextUITwo() {
        val timerTwo: TextView = findViewById(R.id.timerTwo)
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        timerTwo.text = "$minute:$seconds"
    }


    private fun loadConfeti() {
        println("Radi")
        resetTimer()
    }
}