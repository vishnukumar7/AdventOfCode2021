package com.app.adventofcode

import android.util.Log

class HydrothermalVenture(private var listItem: ArrayList<String>) {
private val TAG="HydrothermalVenture"
    private var boardPartOne: Array<IntArray> = Array(10) { IntArray(10) }
    private var boardPartTwo: Array<IntArray> = Array(10) { IntArray(10) }
    private var startPoints = ArrayList<String>()
    private var endPoints = ArrayList<String>()
    private var larger=0

    fun init() {

        for (values in listItem) {
            val points = values.split("->") as ArrayList<String>
            val s = points[0].trim().split(",")
            var x1 = s[0].toInt()
            var y1 = s[1].toInt()
            val e = points[1].trim().split(",")
            var x2 = e[0].toInt()
            var y2 = e[1].toInt()
            if(larger<x1)
                larger=x1

            if(larger<y1)
                larger=y1

            if(larger<x2)
                larger=x2

            if(larger<y2)
                larger=y2
            if(x1==x2 || y2==y1){
                startPoints.add(points[0].trim())
                endPoints.add(points[1].trim())

            }
            else{
                startPoints.add(0,points[0].trim())
                endPoints.add(0,points[1].trim())
            }
        }
        boardPartOne= Array(larger+1) { IntArray(larger+1) }
        boardPartTwo= Array(larger+1) { IntArray(larger+1) }
        for (x in 0 until larger+1) {
            for (y in 0 until larger+1) {
                boardPartOne[x][y] = 0
                boardPartTwo[x][y] = 0
            }
        }
    }

    fun partOne() {
        var pos = 0
        var points=0
        while (pos < startPoints.size) {
            val s = startPoints[pos].split(",")
            var x1 = s[0].toInt()
            var y1 = s[1].toInt()
            val e = endPoints[pos].split(",")
            var x2 = e[0].toInt()
            var y2 = e[1].toInt()
            pos++
            var flag=false
            if (x1 == x2) {
                if (y1 < y2) {
                    for (data in y1 until y2 + 1) {
                        boardPartOne[x1][data]=boardPartOne[x1][data]+1
                    }
                } else {
                    for (data in y2 until y1 + 1) {
                        boardPartOne[x1][data]=boardPartOne[x1][data]+1
                    }
                }
            } else if (y1 == y2) {
                if (x1 < x2) {
                    for (data in x1 until x2 + 1) {
                        boardPartOne[data][y1]=boardPartOne[data][y1]+1
                    }
                } else {
                    for (data in x2 until x1 + 1) {
                        boardPartOne[data][y1]=boardPartOne[data][y1]+1
                    }
                }
            }
           /* Log.d(TAG, "partOne: ${startPoints[pos-1]} ${endPoints[pos-1]}")
            if(flag) {
                Log.d(TAG, "partOne: overlap ${startPoints[pos-1]} ${endPoints[pos-1]}")
                points++
            }*/
          //  printArray()
        }
        printArrayPartOne()
    }

    fun partTwo() {
        var pos = 0
        var points=0
        while (pos < startPoints.size) {
            val s = startPoints[pos].split(",")
            var x1 = s[0].toInt()
            var y1 = s[1].toInt()
            val e = endPoints[pos].split(",")
            var x2 = e[0].toInt()
            var y2 = e[1].toInt()
            pos++
            var flag=false
            if (x1 == x2) {
                if (y1 < y2) {
                    for (data in y1 until y2 + 1) {
                        boardPartTwo[x1][data]=boardPartTwo[x1][data]+1
                    }
                } else {
                    for (data in y2 until y1 + 1) {
                        boardPartTwo[x1][data]=boardPartTwo[x1][data]+1
                    }
                }
            } else if (y1 == y2) {
                if (x1 < x2) {
                    for (data in x1 until x2 + 1) {
                        boardPartTwo[data][y1]=boardPartTwo[data][y1]+1
                    }
                } else {
                    for (data in x2 until x1 + 1) {
                        boardPartTwo[data][y1]=boardPartTwo[data][y1]+1
                    }
                }
            }else {
                if(x1<x2 && y1<y2){
                    while (x1<=x2){
                        boardPartTwo[x1][y1]=boardPartTwo[x1][y1]+1
                        x1++
                        y1++
                    }
                }else if(x1>x2 && y1<y2){
                    while (x1>=x2){
                        boardPartTwo[x1][y1]=boardPartTwo[x1][y1]+1
                        x1--
                        y1++
                    }
                }else if(x1>x2 && y1>y2){
                    while (x1>=x2){
                        boardPartTwo[x1][y1]=boardPartTwo[x1][y1]+1
                        x1--
                        y1--
                    }
                }else if(x1<x2 && y1>y2){
                    while (x1<=x2){
                        boardPartTwo[x1][y1]=boardPartTwo[x1][y1]+1
                        x1++
                        y1--
                    }
                }
            }
        }
        printArrayPartTwo()
    }


    fun printArrayPartOne(){
        println("print data :" )
        var sum=0
        for (x in 0 until larger+1) {
            for (y in 0 until larger+1) {
               // print("${boardPartOne[x][y]} ")
                if(boardPartOne[x][y]>1)
                    sum++
            }
         //   println()
        }
        Log.d(TAG, "printArray: sum : $sum")
    }

    fun printArrayPartTwo(){
        println("print data :" )
        var sum=0
        for (x in 0 until larger+1) {
            for (y in 0 until larger+1) {
               // print("${boardPartTwo[x][y]} ")
                if(boardPartTwo[x][y]>1)
                    sum++
            }
         //   println()
        }
        Log.d(TAG, "printArray: sum : $sum")
    }


}