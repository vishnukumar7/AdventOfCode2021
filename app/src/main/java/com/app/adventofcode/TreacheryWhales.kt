package com.app.adventofcode

import android.util.Log
import kotlin.math.abs

class TreacheryWhales(private var listItem: ArrayList<String>) {
private val TAG="TreacheryWhales"
    private var smaller: Long= Long.MAX_VALUE
    private var larger: Long= Long.MIN_VALUE

    fun init(){
        var array=listItem[0].split(",")
        for(x in array){
            if(x.toLong()<smaller){
                smaller=x.toLong()
            }
            if(x.toLong()>larger){
                larger=x.toLong()
            }
        }
    }

    fun partOne(){
        var array=listItem[0].split(",")
        var small=Long.MAX_VALUE
        for(x in smaller..larger){
            var sum: Long=0
            for(data in array){
                sum+= abs(data.toLong()-x)
            }
            Log.d(TAG, "partOne: $x in sum of $sum")
            if(small>sum)
            {
                small=sum
            }
        }
        Log.d(TAG, "partOne: smaller fuel : $small")
    }

    fun partTwo(){
        var array=listItem[0].split(",")
        var small=Long.MAX_VALUE
        for(x in smaller..larger){
            var sum: Long=0
            for(data in array){
                var sumN= abs(data.toLong()-x)
                sum+=((sumN)*(sumN+1)/2)
            }
            Log.d(TAG, "partTwo: $x in sum of $sum")
            if(small>sum)
            {
                small=sum
            }
        }
        Log.d(TAG, "partTwo: smaller fuel : $small")
    }

}