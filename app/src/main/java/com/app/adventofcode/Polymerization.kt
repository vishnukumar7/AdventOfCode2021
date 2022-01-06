package com.app.adventofcode

import android.util.Log

class Polymerization(private val listItem: ArrayList<ArrayList<String>>) {
private val TAG="Polymerization"

    private var chartItem= listItem[1].associate {
        val data=it.split("->")
        val pattern=data[0].trim()
        val plus=data[1].trim()
        val replace = arrayOf(pattern[0].toString() + plus, plus + pattern[1])
        pattern to replace
    } as HashMap
    private var first='0'
    fun partOne(): Long {
        var step=0
        first=listItem[0][0][0]
        var templateMap: Map<String, Long> = createStartingMap(listItem[0][0])
        while (step++!=10){
            templateMap=transform(templateMap)
            Log.d(TAG, "part2: $templateMap")
        }
        return countChars(templateMap)
    }


    fun partTwo(): Long {
        var step=0
        first=listItem[0][0][0]
        var templateMap: Map<String, Long> = createStartingMap(listItem[0][0])
        while (step++!=40){
            templateMap=transform(templateMap)
            Log.d(TAG, "part2: $templateMap")
        }
        return countChars(templateMap)
    }

    private fun createStartingMap(line: String): Map<String, Long> {
        val map: MutableMap<String, Long> = HashMap()
        for (i in 0 until line.length - 1) {
            val s = line.substring(i, i + 2)
            val n = map.getOrDefault(s, 0L)
            map[s] = n + 1
        }
        return map
    }

    private fun transform(record: Map<String, Long>): Map<String, Long> {
        val newRecord: HashMap<String, Long> = HashMap()
        for (key in record.keys) {
            val countKey = record[key]!!
            if (chartItem.containsKey(key)) {
                val insert1: String = chartItem[key]!![0]
                val insert2: String = chartItem[key]!![1]
                val count1 = newRecord.getOrDefault(insert1, 0L)
                val count2 = newRecord.getOrDefault(insert2, 0L)
                newRecord[insert1] = count1 + countKey
                newRecord[insert2] = count2 + countKey
            } else {
                newRecord[key] = countKey
            }
        }
        return newRecord
    }

    private fun countChars(templateMap: Map<String, Long>): Long {
        val charMap: MutableMap<Char, Long> = HashMap()
        val firstN = charMap.getOrDefault(first, 0L)
        charMap[first] = firstN + 1
        var small=Long.MAX_VALUE
        var larger=0L
        for (key in templateMap.keys) {
            val c = key[1]
            val n = templateMap[key]
            val charN = charMap.getOrDefault(c, 0L)
            val  values=charN + n!!
            charMap[c] = values
        }

        for(item in charMap.keys){
            if (small>(charMap[item]!!)){
                small=charMap[item]!!
            }

            if(larger<(charMap[item]!!)){
                larger=charMap[item]!!
            }
        }
        return larger-small
    }
}