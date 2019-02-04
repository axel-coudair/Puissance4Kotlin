package com.example.axel.puissance4.presentation

import com.example.axel.puissance4.model.Player

interface P4View {

    fun setTurnText(player: Player)
    fun setNamesText(player1: Player, player2: Player)
    fun showCannotAddToken()
}