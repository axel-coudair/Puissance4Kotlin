package com.example.axel.puissance4.model


data class Token(val player: Player?, var x: Int, var y: Int ?= null) {
    constructor(player: Player, x: Int) : this(player, x, null)

    fun getTagFormat(): String {
        return this.x.toString() + this.y.toString()
    }
}