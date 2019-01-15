package com.example.axel.puissance4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btnPlay)

        button.setOnClickListener {
            onPlayClick(it)
        }
    }

    fun onPlayClick(view: View) {
        val errorMessageTextView = findViewById<TextView>(R.id.textViewError)
        val firstPlayerName = findViewById<EditText>(R.id.editTextPlayer1)
        val secondPlayerName = findViewById<EditText>(R.id.editTextPlayer2)
        var firstPlayerNameText = firstPlayerName.text.toString().trim()
        var secondPlayerNameText = secondPlayerName.text.toString().trim()
        if (!firstPlayerNameText.isNullOrBlank() && !secondPlayerNameText.isNullOrBlank()) {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("firstPlayerName", firstPlayerNameText)
            intent.putExtra("secondPlayerName", secondPlayerNameText)
            startActivity(intent)
        } else
            errorMessageTextView.visibility = View.VISIBLE

    }
}
