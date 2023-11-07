package com.example.catchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginTop
import com.example.catchthekenny.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var timer: CountDownTimer
    private var score: Int = 0
    private val ONE_SECOND = 1000L
    private val TEN_SECONDS = ONE_SECOND * 10L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        timer = object : CountDownTimer(TEN_SECONDS, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timerText.text = "Seconds Remaining : ${millisUntilFinished.floorDiv(1000)}"
            }

            override fun onFinish() {
                binding.timerText.text = "Times Up!"
                showFinalAlert(score);
            }
        }

        binding.score.text = "Score : $score"

    }

    override fun onStart() {
        super.onStart()
        timer.start()
    }

    fun showFinalAlert(score: Int) {
        val alertDialog = AlertDialog.Builder(this@MainActivity).setTitle("Game Over").setCancelable(false)
            .setMessage("Your score is $score").setPositiveButton("Restart") { _, _ ->
                restartGame()
            }
        alertDialog.show()
    }

    fun onClickKenny(view: View){
        incrementScore()
        moveKenny()
    }

    private fun incrementScore() {
        score += 1
        binding.score.text = "Score : $score"
    }

    private fun moveKenny(){
        binding.kenny.translationX = Random.nextInt(3,binding.root.width - binding.kenny.width).toFloat()
        binding.kenny.translationY = Random.nextInt(3,binding.root.height - binding.kenny.height).toFloat()
    }

    private fun restartGame(){
        score = 0
        binding.score.text = "Score : $score"
        timer.start()
    }

}