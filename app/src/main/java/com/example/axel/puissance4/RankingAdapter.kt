package com.example.axel.puissance4

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.axel.puissance4.model.Player
import kotlinx.android.synthetic.main.ranking_list_item.view.*

class RankingAdapter(val items : ArrayList<Player>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.ranking_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = items.get(position)
        holder?.playerName?.text = player.name
        holder?.playerVictory?.text = player.victory.toString()
        holder?.playerDefeat?.text = player.defeat.toString()

    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val playerName = view.tv_playerName
    val playerVictory = view.tv_playerVictory
    val playerDefeat = view.tv_playerDefeat
}