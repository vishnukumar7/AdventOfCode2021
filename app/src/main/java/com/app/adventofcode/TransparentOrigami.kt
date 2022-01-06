package com.app.adventofcode

import android.util.Log
import kotlin.math.abs

class TransparentOrigami(private var listItem: ArrayList<ArrayList<String>>) {

    private val TAG="TransparentOrigami"
    private var page =listItem[0].map {
        val c=it.split(",")
        Coord(x= c[0].toInt(),y= c[1].toInt())
    }
    private val instructions =listItem[1].map {
        Pair(it[it.indexOf("=") - 1], it.slice(it.indexOf("=") + 1 until it.length).toInt())
    }

    fun partOne(): Int {
        page= page.foldBy(instructions[0].first,instructions[0].second) as ArrayList<Coord>
       return page.size
    }

    fun partTwo(){
        instructions.forEach {
            page=page.foldBy(it.first,it.second) as ArrayList<Coord>
        }
        Log.d(TAG, "partTwo: ")
        page.print()
    }

    fun List<Coord>.print() {
        println()
        val width = maxOf { it.x }
        val height = maxOf { it.y }

        val p = Array(size = height + 1) {
            Array(size = width + 1) { '.' }
        }

        forEach { p[it.y][it.x] = '#' }

        p.forEach { x ->
            x.forEach {
                print(it)
            }
            println()
        }
        println()
    }

    fun List<Coord>.foldBy(axis: Char, foldLine: Int): List<Coord> {
        val flippers =
            if (axis == 'x') filter { it.x > foldLine }
            else filter { it.y > foldLine }

        flippers.forEach {
            if (axis == 'x') it.x = abs(it.x - (foldLine * 2))
            else it.y = abs(it.y - (foldLine * 2))
        }

        return this.distinct()
    }
}