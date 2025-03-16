package com.example.proyect1_p1

// src/main/java/com/yourpackage/Practice2Activity.kt
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Practice2Activity : AppCompatActivity() {
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_2)

        val textViewCounter = findViewById<TextView>(R.id.textView_counter)

        findViewById<Button>(R.id.button_increment).setOnClickListener {
            counter++
            textViewCounter.text = "Contador: $counter"
        }

        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish() // Regresa al menú
        }
    }
}
