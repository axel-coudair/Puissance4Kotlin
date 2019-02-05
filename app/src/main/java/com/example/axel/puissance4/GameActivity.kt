package com.example.axel.puissance4

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.axel.puissance4.model.Player
import com.example.axel.puissance4.model.Token
import com.example.axel.puissance4.presentation.P4Presenter
import com.example.axel.puissance4.presentation.P4View
import kotlinx.android.synthetic.main.activity_game.*
import android.view.View

class GameActivity : P4View, AppCompatActivity() {

    private val presenter: P4Presenter = P4Presenter(this)
    private lateinit var restartButton: Button
    private lateinit var quitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        presenter.startGame()
        initMenuButton()
    }

    fun initMenuButton() {
        restartButton = findViewById(R.id.restartButton)
        quitButton = findViewById(R.id.quitButton)
        restartButton.setOnClickListener { v -> onRestartCliked(v as Button) }
        quitButton.setOnClickListener { v -> onQuitCliked(v as Button) }
    }

    override fun setTurnText(player: Player) {
        turnTextView.text = ("Au tour de ${player.name} ")
    }

    override fun setNamesText(player1: Player, player2: Player) {
        player1TextView.text = "${player1.name} : ${player1.score}"
        player2TextView.text = "${player2.name} : ${player2.score}"
    }

    override fun showCannotAddToken() {
        Toast.makeText(this, "Vous ne pouvez plus ajouter de jeton à la colonne souhaité", Toast.LENGTH_SHORT).show()
    }

    override fun displayWinner(name: String) {
        Toast.makeText(this, "C'est gagné ${name} !", Toast.LENGTH_LONG).show()
    }

    private fun onRestartCliked(view: Button) {
        presenter.startAGamePlay()
    }

    private fun onQuitCliked(view: Button) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
