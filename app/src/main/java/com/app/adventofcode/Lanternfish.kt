package com.app.adventofcode

import android.util.Log

class Lanternfish(private var currentItem: String) {
    private val TAG = "Lanternfish"
    private var baseItem: LongArray = longArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

    fun partOne() {
        val arrays = currentItem.split(",")
        for (x in arrays) {
            baseItem[x.toInt()] = baseItem[x.toInt()] + 1
        }

        for (range in 0 until 80) {
            val newBaseItem: LongArray = longArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
            for (x in baseItem.indices) {
                if (x == 0) {
                    newBaseItem[6] += baseItem[x]
                    newBaseItem[8] += baseItem[x]
                } else {
                    newBaseItem[x - 1] += baseItem[x]
                }
            }
            baseItem = newBaseItem
        }
        var sum: Long = 0
        for (x in baseItem.indices) {
            sum += baseItem[x]
        }

        Log.d(TAG, "anotherMethod: sum $sum")
    }

    fun partTwo() {
        val arrays = currentItem.split(",")
        for (x in arrays) {
            baseItem[x.toInt()] = baseItem[x.toInt()] + 1
        }

        for (range in 0 until 256) {
            val newBaseItem: LongArray = longArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
            for (x in baseItem.indices) {
                if (x == 0) {
                    newBaseItem[6] += baseItem[x]
                    newBaseItem[8] += baseItem[x]
                } else {
                    newBaseItem[x - 1] += baseItem[x]
                }
            }
            baseItem = newBaseItem
        }
        var sum: Long = 0
        for (x in baseItem.indices) {
            sum += baseItem[x]
        }

        Log.d(TAG, "anotherMethod: sum $sum")
    }


}