package com.app.adventofcode

import android.util.Log

class DiveDayTwo(private var listItem : ArrayList<String>){
    private val TAG="DiveDayTwo"

    fun partOne(){
        var start : Long=0
        var depth: Long=0
        for(item in listItem){
            val itemValue=item.trim().split(" ")
            Log.d(TAG, "getPilot: $item")
            val dataKeys=itemValue[0]
            val dataValues=itemValue[1].toInt()
            if(dataKeys=="forward"){
                start+=dataValues.toLong()
            }else if (dataKeys == "down"){
                depth+=dataValues.toLong()
            }else if(dataKeys=="up"){
                depth-=dataValues.toLong()
            }
            Log.d(TAG, "getPilot: $start $depth")
        }
        Log.d(TAG, "getPilot: pilot ${(start*depth)}")
    }

    fun partTwo(){
        var start : Long=0
        var aim: Long=0
        var depth: Long=0
        for(item in listItem){
            val itemValue=item.trim().split(" ")
            Log.d(TAG, "getPilot: $item")
            val dataKeys=itemValue[0]
            val dataValues=itemValue[1].toInt()
            if(dataKeys=="forward"){
                start+=dataValues.toLong()
                depth+=(aim*dataValues).toLong()
            }else if (dataKeys == "down"){
                aim+=dataValues.toLong()
            }else if(dataKeys=="up"){
                aim-=dataValues.toLong()
            }
            Log.d(TAG, "getPilot: $start $depth $aim")
        }
        Log.d(TAG, "getPilot: pilot ${(start*depth)}")
    }
}