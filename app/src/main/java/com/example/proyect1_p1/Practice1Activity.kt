package com.example.proyect1_p1

// src/main/java/com/yourpackage/Practice1Activity.kt
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Practice1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_1)

        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish() // Regresa al menú
        }
    }
}
