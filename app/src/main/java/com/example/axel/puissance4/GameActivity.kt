package com.example.axel.puissance4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.axel.puissance4.model.Player
import com.example.axel.puissance4.presentation.P4Presenter
import com.example.axel.puissance4.presentation.P4View
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : P4View, AppCompatActivity() {

    private val presenter: P4Presenter = P4Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        presenter.startGame()
    }

    override fun setTurnText(player: Player) {
        turnTextView.text = ("Au tour de ${player.name} ")
    }

    override fun setNamesText(player1: Player, player2: Player) {
        player1TextView.text = player1.name
        player2TextView.text = player2.name
    }

    override fun showCannotAddToken() {
        Toast.makeText(this, "Vous ne pouvez plus ajouter de jeton à la colonne souhaité", Toast.LENGTH_SHORT).show()
    }
}
