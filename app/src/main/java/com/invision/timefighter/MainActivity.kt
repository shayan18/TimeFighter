package com.invision.timefighter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    internal lateinit var hitMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    internal  var score = 0
    internal var gameStarted = false
    internal  lateinit var countDownTimer: CountDownTimer
    internal  val initialCountDown : Long = 10000
    internal  val countDownInterval : Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hitMeButton = findViewById(R.id.hit_me_button)
        gameScoreTextView = findViewById(R.id.game_score_textview)
        timeLeftTextView = findViewById(R.id.timeleft_textview)
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
        resetGame()

        hitMeButton.setOnClickListener { view ->

            incrementScore()
        }

    }
    private  fun startGame() {
        if (!gameStarted) {

          //  hitMeButton.text = findViewById(R.id.hit_me_button, "Tap!!!!")
            gameStarted = true
            countDownTimer.start()
        }
    }
    private fun endGameWithMsg() {
      //  Toast.makeText(this, "Hi there! This is a Toast.", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, text: getString(R.string.your_score, score.toString()), Toast.LENGTH_SHORT).show()


    }

    private fun incrementScore() {
        startGame()
        score += 1
        val newScore = getString(R.string.your_score, score.toString())
        gameScoreTextView.text = newScore
    }
    private  fun resetGame() {
        score = 0
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
        val initialTimeLeft = initialCountDown /1000
        timeleft_textview.text = getString(R.string.time_left, initialTimeLeft.toString())
        countDownTimer = object: CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val timeleft = millisUntilFinished / 1000
                timeleft_textview.text = getString(R.string.time_left, timeleft.toString())
            }

            override fun onFinish() {
                resetGame()
                endGameWithMsg()

            }
        }
        gameStarted = false

}


}
