
package com.example.proyect1_p1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "app_data.db"
        private const val DATABASE_VERSION = 1

        // Tabla de personas
        const val TABLE_PERSONS = "persons"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "nombre"
        const val COLUMN_AGE = "edad"

        // Tabla de puntuaciones
        const val TABLE_SCORES = "scores"
        const val COLUMN_SCORE = "puntuacion"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createPersonsTable = ("CREATE TABLE $TABLE_PERSONS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_AGE INTEGER)")
        db.execSQL(createPersonsTable)

        val createScoresTable = ("CREATE TABLE $TABLE_SCORES ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_SCORE INTEGER)")
        db.execSQL(createScoresTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PERSONS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SCORES")
        onCreate(db)
    }

    // Insertar persona en la tabla de personas
    fun insertPerson(name: String, age: Int): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_AGE, age)
        }
        return db.insert(TABLE_PERSONS, null, contentValues)
    }

    // Obtener todas las personas
    fun getAllPersons(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_PERSONS", null)
    }

    // Insertar puntuación en la tabla de puntuaciones
    fun insertScore(name: String, score: Int): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_SCORE, score)
        }
        return db.insert(TABLE_SCORES, null, contentValues)
    }

    // Obtener las mejores 5 puntuaciones
    fun getTopScores(limit: Int = 5): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_SCORES ORDER BY $COLUMN_SCORE DESC LIMIT $limit", null)
    }
}
