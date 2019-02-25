package com.example.axel.puissance4
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.axel.puissance4.model.Player
import kotlinx.android.synthetic.main.activity_ranking.*
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class RankingActivity : AppCompatActivity() {

    val players: ArrayList<Player> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        database.use {
            select("Player")
                .exec {
                    parseList(object : MapRowParser<Map<String, Any?>> {
                        override fun parseRow(columns: Map<String, Any?>): Map<String, Any?> {
                            players.add(Player(
                                columns["name"].toString(),
                                columns["victory"].toString().toIntOrNull()!!,
                                columns["defeat"].toString().toIntOrNull()!!
                            ))
                            return columns
                        }
                    })
                }
        }

        // Creates a vertical Layout Manager
        list_ranking.layoutManager = GridLayoutManager(this, 1)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it
        list_ranking.adapter = RankingAdapter(players, this)

    }

}