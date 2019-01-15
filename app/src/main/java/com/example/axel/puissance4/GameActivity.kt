package com.example.axel.puissance4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    override fun onStart() {
        super.onStart()
        var firstPlayer = findViewById<TextView>(R.id.player1TextView)
        var secondPlayer = findViewById<TextView>(R.id.player2TextView)

        var firstPlayerName: String = intent.getStringExtra("firstPlayerName")
        var secondPlayerName: String = intent.getStringExtra("secondPlayerName")

        firstPlayer.text = firstPlayerName
        secondPlayer.text = secondPlayerName
    }
}
