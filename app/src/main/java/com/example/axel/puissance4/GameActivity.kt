package com.example.axel.puissance4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.axel.puissance4.model.Player
import com.example.axel.puissance4.presentation.P4View
import com.example.axel.puissance4.presentation.P4Presenter

class GameActivity : P4View, AppCompatActivity() {

    private val presenter: P4Presenter = P4Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        presenter.startGame()
    }

    override fun getPlayer1Name(): String {
        return intent.getStringExtra("firstPlayerName")
    }

    override fun getPlayer2Name(): String {
        return intent.getStringExtra("secondPlayerName")
    }

    override fun setNameLabel(player1: Player, player2: Player) {

        var firstPlayerTextView = findViewById<TextView>(R.id.player1TextView)
        var secondPlayerTextView = findViewById<TextView>(R.id.player2TextView)

        firstPlayerTextView.text = player1.name
        secondPlayerTextView.text = player2.name
    }
}
