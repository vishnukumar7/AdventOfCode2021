package com.app.adventofcode

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class SyntaxScoring(private var listItem: ArrayList<String>) {
private val TAG="SyntaxScoring"
    private val A=3
    private var sumA=0
    private val B=57
    private var sumB=0
    private val C=1197
    private var sumC=0
    private val D=25137
    private var sumD=0


    private var pointsA: Long=1
    private var pointsB: Long=2
    private var pointsC: Long=3
    private var pointsD: Long=4

    private var sumPoints: Long=0
    private val inCompleteLine: ArrayList<String> = ArrayList()
    private val completeValue: ArrayList<Long> = ArrayList()

    fun partOne(){
        for (value in listItem){
            val data=checkPartOne(value.toCharArray().toList() as ArrayList<Char>)
            Log.d(TAG, "partOne: $data")
            when(data){
                ')' -> sumA++
                '}' -> sumC++
                ']' -> sumB++
                '>' -> sumD++
                else -> {
                    Log.d(TAG, "inCompleteLine: $value")
                    //inCompleteLine.add(value)
                }
            }
        }
        Log.d(TAG, "partOne: $sumA $sumB $sumC $sumD")
        val sum=(A*sumA) + (B*sumB) + (C*sumC) + (D*sumD)
        Log.d(TAG, "partOne: sum : $sum")
        partTwo()
    }

    private fun partTwo(){
        for (value in inCompleteLine){
            sumPoints=0
            val data=charPartTwo(value.toCharArray().toList() as ArrayList<Char>)
            for(x in data.indices){
                when (data[x]) {
                    ')' -> {
                        sumPoints*=5
                        sumPoints+=pointsA
                    }
                    '}' -> {
                        sumPoints*=5
                        sumPoints+=pointsC
                    }
                    ']' -> {
                        sumPoints*=5
                        sumPoints+=pointsB
                    }
                    '>' -> {
                        sumPoints*=5
                        sumPoints+=pointsD
                    }
                }
            }
            Log.d(TAG, "partTwo: sumpoints  $sumPoints")
            completeValue.add(sumPoints)
        }

        completeValue.sort()
        Log.d(TAG, "partTwo: ${completeValue[((completeValue.size)/2).toInt()]}")
    }

    private fun charPartTwo(item: ArrayList<Char>): String{
        var data=""
        for(index in item.size-1 downTo 0){
            val element=item[index]
            when (element) {
                '(' -> {
                   data+=")"
                }
                '{' -> {
                    data+="}"
                }
                '[' -> {
                    data+="]"
                }
                '<' -> {
                    data+=">"
                }
            }
        }
        return data
    }


    private fun checkPartOne(item : ArrayList<Char>) : Char{
        for((index, element) in item.withIndex()){
            if(index==0){
                when (element) {
                    ')' -> {
                        return element
                    }
                    '}' -> {
                        return element
                    }
                    ']' -> {
                        return element
                    }
                    '>' -> {
                        return element
                    }
                }
            }
            when (element) {
                ')' -> {
                    return if(item[index-1] =='('){
                        item.removeAt(index)
                        item.removeAt(index-1)
                        checkPartOne(item)
                    }else {
                        element
                    }
                }
                '}' -> {
                    return if(item[index-1] =='{'){
                        item.removeAt(index)
                        item.removeAt(index-1)
                        checkPartOne(item)
                    }else {
                        element
                    }
                }
                ']' -> {
                    return if(item[index-1] =='['){
                        item.removeAt(index)
                        item.removeAt(index-1)
                        checkPartOne(item)
                    }else {
                        element
                    }
                }
                '>' -> {
                    return if(item[index-1] =='<'){
                        item.removeAt(index)
                        item.removeAt(index-1)
                        checkPartOne(item)
                    }else {
                        element
                    }
                }
            }
        }
        inCompleteLine.add(item.joinToString(""))
        return '-'
    }

}