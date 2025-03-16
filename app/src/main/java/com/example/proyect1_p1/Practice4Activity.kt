package com.example.proyect1_p1
// src/main/java/com/yourpackage/Practice4Activity.kt
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class Practice4Activity : AppCompatActivity() {
    private var counter = 0
    private var isButtonEnabled = true
    private var timeLeft = 10 // Tiempo en segundos
    private val fileName = "historial.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_4)

        val textViewCounter = findViewById<TextView>(R.id.textView_counter)
        val textViewTimer = findViewById<TextView>(R.id.textView_timer)
        val buttonIncrement = findViewById<Button>(R.id.button_increment)

        // Incrementar el contador si el botón está habilitado
        buttonIncrement.setOnClickListener {
            if (isButtonEnabled) {
                counter++
                textViewCounter.text = "Contador: $counter"
            }
        }

        // Manejar el temporizador de 10 segundos y actualizar la pantalla
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                if (timeLeft > 0) {
                    timeLeft--
                    textViewTimer.text = "Tiempo: ${timeLeft}s"
                    handler.postDelayed(this, 1000)
                } else {
                    isButtonEnabled = false
                    saveToFile(counter) // Guardar el puntaje al final del tiempo
                    showTopScoresDialog() // Mostrar los mejores puntajes al final del juego
                }
            }
        }
        handler.post(runnable)

        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish() // Regresa al menú
        }
    }

    // Guardar el puntaje en el archivo
    private fun saveToFile(score: Int) {
        val file = File(filesDir, fileName)
        file.appendText("$score\n")
    }

    // Leer y ordenar los puntajes, mostrando los 5 mejores en un AlertDialog
    private fun showTopScoresDialog() {
        val file = File(filesDir, fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
        val scores = file.readLines().mapNotNull { it.toIntOrNull() }.sortedDescending().take(5)

        val scoreText = if (scores.isNotEmpty()) {
            scores.joinToString("\n") { "Puntaje: $it" }
        } else {
            "No hay puntajes registrados."
        }

        // Crear el AlertDialog para mostrar los mejores puntajes y la opción de reiniciar
        AlertDialog.Builder(this)
            .setTitle("Mejores 5 Puntajes")
            .setMessage(scoreText)
            .setPositiveButton("Reiniciar") { _, _ ->
                restartGame()
            }
            .setNegativeButton("Volver al Menú") { _, _ ->
                finish()
            }
            .show()
    }

    // Reiniciar el juego: contador, tiempo y botón habilitado
    private fun restartGame() {
        counter = 0
        timeLeft = 10
        isButtonEnabled = true

        val textViewCounter = findViewById<TextView>(R.id.textView_counter)
        val textViewTimer = findViewById<TextView>(R.id.textView_timer)

        textViewCounter.text = "Contador: $counter"
        textViewTimer.text = "Tiempo: ${timeLeft}s"

        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                if (timeLeft > 0) {
                    timeLeft--
                    textViewTimer.text = "Tiempo: ${timeLeft}s"
                    handler.postDelayed(this, 1000)
                } else {
                    isButtonEnabled = false
                    saveToFile(counter)
                    showTopScoresDialog()
                }
            }
        }
        handler.post(runnable)
    }
}
