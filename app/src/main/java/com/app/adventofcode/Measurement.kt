package com.app.adventofcode

import android.util.Log

class Measurement(private var listItem: ArrayList<String>) {

    private val TAG="Measurement"
    fun partOne(){
        var anwser=0
        for(item in 1 until listItem.size){
            val prev=listItem[item-1].toLong()
            val curr=listItem[item].toLong()
            if(curr>prev)
                anwser++
        }
        Log.d(TAG, "partOne: $anwser")
    }

    fun partTwo(){
        var anwser=0
        var sum:Long=-1
        for(item in 2 until listItem.size){
            val prev=listItem[item-1].toLong()
            val prev1=listItem[item-2].toLong()
            val curr=listItem[item].toLong()
            if(sum<(prev+prev1+curr))
                anwser++
            sum= (prev+prev1+curr)
        }

        Log.d(TAG, "partTwo: $anwser")
    }
}