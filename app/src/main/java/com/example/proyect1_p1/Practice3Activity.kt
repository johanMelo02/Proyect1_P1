package com.example.proyect1_p1
// src/main/java/com/yourpackage/Practice3Activity.kt
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Practice3Activity : AppCompatActivity() {
    private var counter = 0
    private var isButtonEnabled = true
    private var timeLeft = 10 // Tiempo en segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_3)

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
                    showAlertDialog(counter)
                }
            }
        }
        handler.post(runnable)

        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish() // Regresa al menú
        }
    }

    private fun showAlertDialog(clicks: Int) {
        AlertDialog.Builder(this)
            .setTitle("Tiempo finalizado")
            .setMessage("Total de clicks: $clicks")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}
