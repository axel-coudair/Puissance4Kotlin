package com.example.axel.puissance4.presentation


import android.support.v7.widget.GridLayout
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.axel.puissance4.GameActivity
import com.example.axel.puissance4.R
import com.example.axel.puissance4.model.Player
import com.example.axel.puissance4.model.Token
import com.example.axel.puissance4.model.TokenColor
import com.example.axel.puissance4.model.TokenImg

class P4Presenter(val view: GameActivity) {

    //TODO : Essayer de rendre le code plus élégant (MVP) + Recupérer la combinaison gagnante et l'afficher sur l'UI (avec encadré rouge ?) + Gérer fin de partie

    private lateinit var grid: GridLayout
    private lateinit var menu: View
    private lateinit var playerTurn: Player
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var turnTextView: TextView
    private var arrTokens = ArrayList<Token>()

    fun startGame() {
        grid = view.findViewById(R.id.gridTokens)
        menu = view.findViewById(R.id.restartMenu)
        menu = view.findViewById(R.id.restartMenu)
        player1 = Player(view.intent.getStringExtra("firstPlayerName"), 0, TokenImg.YELLOW, TokenColor.YELLOW)
        player2 = Player(view.intent.getStringExtra("secondPlayerName"), 0, TokenImg.RED, TokenColor.YELLOW)
        startAGamePlay()
    }

    fun startAGamePlay() {

        menu.visibility = View.INVISIBLE
        for (i in 0 until grid.childCount) {
            val token = grid.getChildAt(i) as ImageButton
            token.setOnClickListener { v -> onTokenCliked(v as ImageButton) }
        }
        resetGrid()
        turnTextView = view.findViewById(R.id.turnTextView)
        view.setNamesText(player1, player2)
        switchPlayerTurn()
    }

    private fun resetGrid() {
        for (i in 0 until grid.childCount) {
            val token = grid.getChildAt(i) as ImageButton
            token?.setImageResource(TokenImg.WHITE)
        }
        arrTokens = ArrayList()
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
        if (compteur >= 4){
            handleWin()
        }

    }

    private fun checkLeftAndRight(player: Player, newToken: Token) {
        var compteurRight = 0; var compteurLeft = 0

        while (arrTokens.find { it.x == (newToken.x + compteurRight + 1) && it.y == newToken.y && it.player == player } != null) { // Check right-side
            compteurRight++
        }
        while (arrTokens.find { it.x == (newToken.x - compteurLeft - 1) && it.y == newToken.y && it.player == player } != null) { // Check left-side
            compteurLeft++
        }
        checkWin(compteurLeft, compteurRight)
    }

    private fun checkRising(player: Player, newToken: Token) {
        var compteurTopRight = 0; var compteurBotLeft = 0

        while (arrTokens.find { it.x == (newToken.x + compteurTopRight + 1) && it.y == (newToken.y!! + compteurTopRight + 1) && it.player == player } != null) { // Check top-right-side
            compteurTopRight++
        }
        while (arrTokens.find { it.x == (newToken.x - compteurBotLeft - 1) && it.y == (newToken.y!! - compteurBotLeft - 1) && it.player == player } != null) { // Check bottom-left-side
            compteurBotLeft++
        }

        checkWin(compteurTopRight, compteurBotLeft)
    }

    private fun checkDecresent(player: Player, newToken: Token) {
        var compteurBotRight = 0; var compteurTopLeft = 0
        while (arrTokens.find { it.x == (newToken.x + compteurBotRight + 1) && it.y == (newToken.y!! - compteurBotRight - 1) && it.player == player } != null) { // Check bottom-right-side
            compteurBotRight++
        }
        while (arrTokens.find { it.x == (newToken.x - compteurTopLeft - 1) && it.y == (newToken.y!! + compteurTopLeft + 1) && it.player == player } != null) { // Check top-left-side
            compteurTopLeft++
        }

        checkWin(compteurBotRight, compteurTopLeft)
    }

    private fun checkWin(firstCounter: Int, secondCounter: Int){
        if (firstCounter + secondCounter!! >= 3)
            handleWin()
    }

    private fun handleWin(){
        playerTurn.score++
        showMenu(playerTurn)
        return view.displayWinner(playerTurn.name!!)
    }

    private fun showMenu(player: Player){
        menu.visibility = View.VISIBLE
    }

    private fun handleGameState(player: Player, newToken: Token) {
        checkTopAndBottom(player, newToken)
        checkLeftAndRight(player, newToken)
        checkRising(player, newToken)
        checkDecresent(player, newToken)
        switchPlayerTurn()
    }

    private fun switchPlayerTurn() {
        playerTurn = if (::playerTurn.isInitialized && playerTurn == player1) {
            player2
        } else {
            player1
        }
        view.setTurnText(playerTurn)
    }
}