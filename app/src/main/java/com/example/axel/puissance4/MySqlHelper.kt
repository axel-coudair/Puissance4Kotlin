package com.example.axel.puissance4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MySqlHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "myDb") {

    companion object {
        private var instance: MySqlHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MySqlHelper {
            if (instance == null) {
                instance = MySqlHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("Player", true,
            "_id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "name" to TEXT,
            "victory" to INTEGER,
            "defeat" to INTEGER)
        db.insert(
            "Player",
                "name" to "Kevin",
                "victory" to 0,
                "defeat" to 0
        )
        db.insert(
            "Player",
            "name" to "Axel",
            "victory" to 0,
            "defeat" to 0
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

}

// Access property for Context
val Context.database: MySqlHelper
    get() = MySqlHelper.getInstance(applicationContext)