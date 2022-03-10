package com.app.adventofcode

import android.util.Log
import kotlin.math.min

class DiracDice(listItem: ArrayList<String>) {
    private val TAG = "DiracDice"
    var player1Pos = listItem[0].split(":")[1].trim().toLong()
    var player2Pos = listItem[1].split(":")[1].trim().toLong()
    var player1Score = 0L
    var player2Score = 0L
    var dieRolled = 0
    var rolled = 0

    var played = true
    fun partOne(): Long {
        while (player2Score < 1000 && player1Score < 1000) {
            var sum = 0L
            dieRolled++
            sum += getDieRolled()
            dieRolled++
            sum += getDieRolled()
            dieRolled++
            sum += getDieRolled()
            sum %= 10
            if (played) {
                player1Pos += sum
                player1Pos = if (player1Pos == 10L) player1Pos else player1Pos % 10
                player1Score += player1Pos
            } else {
                player2Pos += sum
                player2Pos = if (player2Pos == 10L) player2Pos else player2Pos % 10
                player2Score += player2Pos
            }
            played=!played
            rolled += 3
        }
        Log.d(
            TAG,
            "partOne: values rolled ==> $rolled and player 1 score ==> $player1Score and player 2 score ==> $player2Score"
        )

        return rolled * min(player1Score,player2Score)
    }


    private fun getDieRolled(): Long {
        dieRolled %= 100
        return if (dieRolled == 0)
            100
        else dieRolled.toLong()
    }


}