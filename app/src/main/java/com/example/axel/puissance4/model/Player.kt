package com.example.axel.puissance4.model

data class Player(var name: String? = null, var score: Int, var tokenImg: Int, var tokenColor: Int, var victory: Int = 0, var defeat: Int = 0) {
    constructor(name: String, victory: Int, defeat: Int) : this(name, -1, -1, -1, victory, defeat)
}
