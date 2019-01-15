package com.example.axel.puissance4.presentation

import com.example.axel.puissance4.GameActivity
import com.example.axel.puissance4.model.Player

class P4Presenter(val view: GameActivity) {

    fun startGame() {
         var player1 = Player(view.getPlayer1Name(), 0)
         var player2 = Player(view.getPlayer2Name(), 0)
         view.setNameLabel(player1, player2)
    }
}