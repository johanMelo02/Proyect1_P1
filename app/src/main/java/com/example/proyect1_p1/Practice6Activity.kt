package com.example.proyect1_p1

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Practice6Activity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private var score = 0
    private lateinit var timer: CountDownTimer
    private var isGameRunning = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_6)

        dbHelper = DatabaseHelper(this)
        val scoreTextView = findViewById<TextView>(R.id.textView_score)
        val timerTextView = findViewById<TextView>(R.id.textView_timer)
        val clickButton = findViewById<Button>(R.id.button_click)

        clickButton.setOnClickListener {
            if (!isGameRunning) {
                startGame(timerTextView, clickButton)
            } else {
                score++
                scoreTextView.text = "Puntuación: $score"
            }
        }
    }

    private fun startGame(timerTextView: TextView, clickButton: Button) {
        score = 0
        isGameRunning = true
        clickButton.text = "Haz clic rápido!"

        timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = "Tiempo restante: ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                isGameRunning = false
                clickButton.text = "Iniciar de nuevo"
                saveScore()
                showTopScoresDialog()
            }
        }.start()
    }

    private fun saveScore() {
        // Guardar la puntuación en SQLite
        dbHelper.insertScore("Jugador", score)
    }

    private fun showTopScoresDialog() {
        val cursor = dbHelper.getTopScores(5)
        val scoresList = mutableListOf<String>()

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
            val score = cursor.getInt(cursor.getColumnIndexOrThrow("puntuacion"))
            scoresList.add("$name - $score")
        }
        cursor.close()

        // Mostrar las 5 mejores puntuaciones en un AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Top 5 Puntuaciones")
        builder.setItems(scoresList.toTypedArray(), null)
        builder.setPositiveButton("Reiniciar") { dialogInterface: DialogInterface, _: Int ->
            resetGame()
        }
        builder.show()
    }

    private fun resetGame() {
        findViewById<TextView>(R.id.textView_score).text = "Puntuación: 0"
        findViewById<TextView>(R.id.textView_timer).text = "Tiempo restante: 10s"
    }
}
