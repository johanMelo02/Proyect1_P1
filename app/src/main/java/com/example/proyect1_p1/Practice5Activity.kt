package com.example.proyect1_p1

import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity

class Practice5Activity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_5)

        val editTextName = findViewById<EditText>(R.id.editText_name)
        val editTextAge = findViewById<EditText>(R.id.editText_age)
        val buttonAdd = findViewById<Button>(R.id.button_add)
        val listViewData = findViewById<ListView>(R.id.listView_data)

        databaseHelper = DatabaseHelper(this)

        buttonAdd.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toInt()
            databaseHelper.insertPerson(name, age)
            displayData(listViewData)
        }

        displayData(listViewData)
    }

    private fun displayData(listView: ListView) {
        val cursor: Cursor = databaseHelper.getAllPersons()
        val from = arrayOf(DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_AGE)
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        val adapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to, 0)
        listView.adapter = adapter
    }
}
