package com.example.fofoca

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FofocaActivity : AppCompatActivity() {
    private lateinit var fofoca: Fofoca
    private lateinit var tvDescription: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var pbTimer: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fofoca)

        this.fofoca = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("Fofoca", Fofoca::class.java)
        } else {
            intent.getSerializableExtra("Fofoca")
        } as Fofoca

        this.tvDescription = findViewById(R.id.tvDescription)
        this.btnTrue = findViewById(R.id.btnTrue)
        this.btnFalse = findViewById(R.id.btnFalse)
        this.pbTimer = findViewById(R.id.pbTimer)

        this.tvDescription.text = fofoca.description

        this.btnTrue.setOnClickListener { guessTrue() }
        this.btnFalse.setOnClickListener { guessFalse() }

        startTimer()
    }

    private fun guessTrue() {
        if (this.fofoca.isTrue) {
            setResult(RESULT_OK)
        } else {
            setResult(RESULT_FIRST_USER)
        }
        finish()
    }

    private fun guessFalse() {
        if (!this.fofoca.isTrue) {
            setResult(RESULT_OK)
        } else {
            setResult(RESULT_FIRST_USER)
        }
        finish()
    }

    private fun startTimer() {
        Thread{
            while (this.pbTimer.progress < 100){
                this.pbTimer.progress += 1
                Thread.sleep(100)
            }
            setResult(RESULT_CANCELED)
            finish()
        }.start()
    }
}