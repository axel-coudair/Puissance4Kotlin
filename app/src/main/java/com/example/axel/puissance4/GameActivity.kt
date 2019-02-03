package com.example.axel.puissance4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import com.example.axel.puissance4.model.Player
import com.example.axel.puissance4.presentation.P4Presenter
import com.example.axel.puissance4.presentation.P4View
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : P4View, AppCompatActivity() {

    private val presenter: P4Presenter = P4Presenter(this)
    private lateinit var grid: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        grid = findViewById(R.id.gridTokens)
        presenter.startGame()

        for (i in 0 until grid.childCount) {
            val token = grid.getChildAt(i) as ImageButton
            token.setOnClickListener { v -> onClickToken(v as ImageButton) }
        }
    }

    private fun onClickToken(view: ImageButton) {
        val tag: String = view.tag.toString()
        val x: Char = tag[0]
        val y: Char = tag[1]
        view.setImageResource(R.drawable.yellow)
    }

    override fun getPlayer1Name(): String {
        return intent.getStringExtra("firstPlayerName")
    }

    override fun setNamesText(player1: Player, player2: Player) {
        player1TextView.text = player1.name
        player2TextView.text = player2.name
    }

    override fun setNameLabel(player1: Player, player2: Player) {
        val firstPlayerTextView = findViewById<TextView>(R.id.player1TextView)
        val secondPlayerTextView = findViewById<TextView>(R.id.player2TextView)

        firstPlayerTextView.text = player1.name
        secondPlayerTextView.text = player2.name
    }
}
