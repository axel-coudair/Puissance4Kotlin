package com.example.axel.puissance4.presentation


import android.support.v7.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.axel.puissance4.GameActivity
import com.example.axel.puissance4.R
import com.example.axel.puissance4.model.Player
import com.example.axel.puissance4.model.Token
import com.example.axel.puissance4.model.TokenColor

class P4Presenter(val view: GameActivity) {

    //TODO : Essayer de rendre le code plus élégant (MVP) + Recupérer la combinaison gagnante et l'afficher sur l'UI (avec encadré rouge ?) + Gérer fin de partie

    private lateinit var grid: GridLayout
    private lateinit var playerTurn: Player
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var turnTextView: TextView
    private val arrTokens = ArrayList<Token>()

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
        val newToken = Token(playerTurn, x.toString().toInt())

        var maxY = -1
        arrTokens.filter { it.x == newToken.x }.forEach { if (it.y!! > maxY) maxY = it.y!! } // find the biggest Y of X
        addTokenToBoard(newToken, maxY)
    }

    private fun addTokenToBoard(newToken: Token, maxY: Int) {
        if (maxY < 5) {
            newToken.y = maxY + 1
            arrTokens.add(newToken)
            val token: ImageButton? = grid.findViewWithTag(newToken.getTagFormat())
            token?.setImageResource(playerTurn.tokenImg)
            handleGameState(playerTurn, newToken)
        } else
            view.showCannotAddToken()
    }

    private fun checkTopAndBottom(player: Player, newToken: Token){
        var compteur = 1

        while (arrTokens.find { it.x == newToken.x && it.y == (newToken.y!! - compteur ) && it.player == player } != null) { // Check bottom-side
            compteur++
        }

        if (compteur >= 4) {
            Toast.makeText(view.baseContext, "C'est gagné ${playerTurn.name} !", Toast.LENGTH_LONG).show()
            return
        } else switchPlayerTurn()
    }

    private fun checkLeftAndRight(player: Player, newToken: Token) {
        var compteurRight = 0; var compteurLeft = 0

        while (arrTokens.find { it.x == (newToken.x + compteurRight + 1) && it.y == newToken.y && it.player == player } != null) { // Check right-side
            compteurRight++
        }
        while (arrTokens.find { it.x == (newToken.x - compteurLeft - 1) && it.y == newToken.y && it.player == player } != null) { // Check left-side
            compteurLeft++
        }

        if (compteurLeft + compteurRight >= 3) {
            Toast.makeText(view.baseContext, "C'est gagné ${playerTurn.name} !", Toast.LENGTH_LONG).show()
            return
        } else switchPlayerTurn()
    }

    private fun checkRising(player: Player, newToken: Token) {
        var compteurTopRight = 0; var compteurBotLeft = 0

        while (arrTokens.find { it.x == (newToken.x + compteurTopRight + 1) && it.y == (newToken.y!! + compteurTopRight + 1) && it.player == player } != null) { // Check top-right-side
            compteurTopRight++
        }
        while (arrTokens.find { it.x == (newToken.x - compteurBotLeft - 1) && it.y == (newToken.y!! - compteurBotLeft - 1) && it.player == player } != null) { // Check bottom-left-side
            compteurBotLeft++
        }

        if (compteurTopRight + compteurBotLeft >= 3) {
            Toast.makeText(view.baseContext, "C'est gagné ${playerTurn.name} !", Toast.LENGTH_LONG).show()
            return
        } else switchPlayerTurn()
    }

    private fun checkDecresent(player: Player, newToken: Token) {
        var compteurBotRight = 0; var compteurTopLeft = 0
        while (arrTokens.find { it.x == (newToken.x + compteurBotRight + 1) && it.y == (newToken.y!! - compteurBotRight - 1) && it.player == player } != null) { // Check bottom-right-side
            compteurBotRight++
        }
        while (arrTokens.find { it.x == (newToken.x - compteurTopLeft - 1) && it.y == (newToken.y!! + compteurTopLeft + 1) && it.player == player } != null) { // Check top-left-side
            compteurTopLeft++
        }

        if (compteurBotRight + compteurTopLeft >= 3) {
            Toast.makeText(view.baseContext, "C'est gagné ${playerTurn.name} !", Toast.LENGTH_LONG).show()
            return
        } else switchPlayerTurn()
    }

    private fun handleGameState(player: Player, newToken: Token) {
        checkTopAndBottom(player, newToken)
        checkLeftAndRight(player, newToken)
        checkRising(player, newToken)
        checkDecresent(player, newToken)
    }

    private fun switchPlayerTurn() {
        playerTurn = if (::playerTurn.isInitialized && playerTurn == player1) {
            player2
        } else
            player1
        view.setTurnText(playerTurn)
    }
}