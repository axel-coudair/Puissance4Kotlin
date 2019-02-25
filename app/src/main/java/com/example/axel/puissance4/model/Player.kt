package com.example.axel.puissance4.model

data class Player(var name: String? = null, var score: Int, var tokenImg: Int, var tokenColor: Int, var victory: Int? = null, var defeat: Int? = null) {
    constructor(name: String, victory: Int?, defeat: Int?) : this(name, -1, -1, -1, victory, defeat)
}
