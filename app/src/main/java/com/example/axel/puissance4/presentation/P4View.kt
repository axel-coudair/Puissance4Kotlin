package com.example.axel.puissance4.presentation

import com.example.axel.puissance4.model.Player

interface P4View {
    fun setNameLabel(player1: Player, player2: Player)
    fun getPlayer1Name(): String
    fun getPlayer2Name(): String
}