package com.example.proyect1_p1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar los botones para iniciar las actividades correspondientes
        findViewById<Button>(R.id.button_practice_1).setOnClickListener {
            startActivity(Intent(this, Practice1Activity::class.java))
        }

        findViewById<Button>(R.id.button_practice_2).setOnClickListener {
            startActivity(Intent(this, Practice2Activity::class.java))
        }

        findViewById<Button>(R.id.button_practice_3).setOnClickListener {
            startActivity(Intent(this, Practice3Activity::class.java))
        }

        findViewById<Button>(R.id.button_practice_4).setOnClickListener {
            startActivity(Intent(this, Practice4Activity::class.java))
        }

        findViewById<Button>(R.id.button_practice_5).setOnClickListener {
            startActivity(Intent(this, Practice5Activity::class.java))
        }

        findViewById<Button>(R.id.button_practice_6).setOnClickListener {
            startActivity(Intent(this, Practice6Activity::class.java))
        }
    }
}

