package com.example.axel.puissance4.presentation


import android.support.v7.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import com.example.axel.puissance4.GameActivity
import com.example.axel.puissance4.R
import com.example.axel.puissance4.model.Player
import com.example.axel.puissance4.model.TokenColor

class P4Presenter(val view: GameActivity) {

    private lateinit var grid: GridLayout
    private lateinit var playerTurn: Player
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var turnTextView: TextView

    fun startGame() {
        grid = view.findViewById(R.id.gridTokens)
        for (i in 0 until grid.childCount) {
            val token = grid.getChildAt(i) as ImageButton
            token.setOnClickListener { v -> onTokenCliked(v as ImageButton) }
        }
        player1 = Player(view.intent.getStringExtra("firstPlayerName"), 0, TokenColor.YELLOW)
        player2 = Player(view.intent.getStringExtra("secondPlayerName"), 0, TokenColor.RED)
        turnTextView = view.findViewById(R.id.turnTextView)
        view.setNamesText(player1, player2)
        switchPlayerTurn()
    }

    private fun onTokenCliked(view: ImageButton) {
        val tag: String = view.tag.toString()
        val x: Char = tag[0]
        val y: Char = tag[1]
        view.setImageResource(playerTurn.tokenImg)
        switchPlayerTurn()
    }

    private fun switchPlayerTurn() {
        playerTurn = if (::playerTurn.isInitialized && playerTurn == player1) {
            player2
        } else
            player1
        view.setTurnText(playerTurn)
    }
}