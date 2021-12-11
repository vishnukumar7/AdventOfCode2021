package com.app.adventofcode

import android.util.Log

class DumboOctopus(private var listItem: ArrayList<String>) {
private val TAG="DumboOctopus"
    private val gridItem: ArrayList<IntArray> = ArrayList()
    private val arrayListFlash: ArrayList<String> = ArrayList()
private var flashes=0
    init {
        for(item in listItem){
            val intArray=toIntArray(item.toCharArray().toList() as ArrayList<Char>)
            gridItem.add(intArray)
        }
    }

    private fun toIntArray(arrayList: ArrayList<Char>): IntArray{
        val intArray=IntArray(arrayList.size)
        for (index in 0 until arrayList.size){
            intArray[index]=Character.getNumericValue(arrayList[index])
        }
        return intArray
    }

    fun partOne(){
        for (index in 0 until 100) {
            arrayListFlash.clear()
            for (x in 0 until gridItem.size) {
                for (y in gridItem[x].indices) {
                 //   Log.d(TAG, "partOne: ${gridItem[x][y]} ")
                    if (gridItem[x][y] >= 9) {
                        checkPartOne(x,y,9)
                    } else if(!arrayListFlash.contains("$x$y")) {
                        gridItem[x][y] = gridItem[x][y] + 1
                    }
                }
            }
         //   printArray()
        }
        Log.d(TAG, "partOne: flashes : $flashes")
    }

    fun partTwo(){
        var flag=true
        var index=0
        while (flag) {
            index++
            arrayListFlash.clear()
            for (x in 0 until gridItem.size) {
                for (y in gridItem[x].indices) {
                    //   Log.d(TAG, "partOne: ${gridItem[x][y]} ")
                    if (gridItem[x][y] >= 9) {
                        checkPartOne(x,y,9)
                    } else if(!arrayListFlash.contains("$x$y")) {
                        gridItem[x][y] = gridItem[x][y] + 1
                    }
                }
            }
            flag=isAllFlash()
        }
        Log.d(TAG, "partTwo: all flashes : $index")
    }

    private fun isAllFlash(): Boolean{
        for (x in 0 until gridItem.size) {
            for (y in gridItem[x].indices) {
                if(gridItem[x][y]!=0)
                    return true
            }
        }
        return false
    }

    private fun  printArray(){
        println("print array: ")
        for(x in 0 until gridItem.size){
            for (y in gridItem[x].indices){
                print("${gridItem[x][y]} ")
            }
            println()
        }
    }

    private fun isCheck(x: Int,y: Int): Int{
        if(!arrayListFlash.contains("$x$y")){
            return gridItem[x][y]+1
        }
        return gridItem[x][y]
    }

    private fun checkPartOne(x: Int, y: Int,beyond: Int){
        if(gridItem[x][y]>=beyond) {
            flashes++
            gridItem[x][y]=0
            arrayListFlash.add("$x$y")
            when {
                x == 0 && y == 0 -> {   // Top left corner
                    gridItem[x + 1][y] = isCheck(x+1,y)
                  checkPartOne(x + 1, y,10)
                    gridItem[x + 1][y + 1] =isCheck(x+1,y+1)
                 checkPartOne(x + 1, y + 1,10)
                    gridItem[x][y + 1] = isCheck(x,y+1)
                 checkPartOne(x, y + 1,10)
                }
                x == 0 && y == gridItem[x].size - 1 -> {  // top right corner
                    gridItem[x + 1][y] = isCheck(x+1,y)
                   checkPartOne(x + 1, y,10)
                    gridItem[x + 1][y - 1] = isCheck(x+1,y-1)
                checkPartOne(x + 1, y - 1,10)
                    gridItem[x][y - 1] = isCheck(x,y-1)
                  checkPartOne(x, y - 1,10)
                }
                x == gridItem.size - 1 && y == 0 -> { // bottom left corner
                    gridItem[x - 1][y] = isCheck(x-1,y)
                  checkPartOne(x - 1, y,10)
                    gridItem[x - 1][y + 1] = isCheck(x-1,y+1)
                 checkPartOne(x - 1, y + 1,10)
                    gridItem[x][y + 1] = isCheck(x,y+1)
                 checkPartOne(x, y + 1,10)
                }
                x == gridItem.size - 1 && y == gridItem[x].size - 1 -> { // bottom right corner
                    gridItem[x - 1][y] = isCheck(x-1,y)
                 checkPartOne(x - 1, y,10)
                    gridItem[x - 1][y - 1] = isCheck(x-1,y-1)
                checkPartOne(x - 1, y - 1,10)
                    gridItem[x][y - 1] = isCheck(x,y-1)
                 checkPartOne(x, y - 1,10)
                }
                x == 0 -> { // top center
                    gridItem[x][y - 1] = isCheck(x,y-1)
                  checkPartOne(x, y - 1,10)
                    gridItem[x + 1][y - 1] = isCheck(x+1,y-1)
                  checkPartOne(x + 1, y - 1,10)
                    gridItem[x + 1][y] = isCheck(x+1,y)
                  checkPartOne(x + 1, y,10)
                    gridItem[x + 1][y + 1] = isCheck(x+1,y+1)
                  checkPartOne(x + 1, y + 1,10)
                    gridItem[x][y + 1] = isCheck(x,y+1)
                  checkPartOne(x, y + 1,10)
                }
                y == 0 -> { // left center
                    gridItem[x - 1][y] = isCheck(x-1,y)
                  checkPartOne(x - 1, y,10)
                    gridItem[x - 1][y + 1] = isCheck(x-1,y+1)
                  checkPartOne(x - 1, y + 1,10)
                    gridItem[x][y + 1] =isCheck(x,y+1)
                  checkPartOne(x, y + 1,10)
                    gridItem[x + 1][y + 1] = isCheck(x+1,y+1)
                  checkPartOne(x + 1, y + 1,10)
                    gridItem[x + 1][y] = isCheck(x+1,y)
                  checkPartOne(x + 1, y,10)
                }
                x == gridItem.size - 1 -> {  // bottom center
                    gridItem[x][y - 1] = isCheck(x,y-1)
                  checkPartOne(x, y - 1,10)
                    gridItem[x - 1][y - 1] = isCheck(x-1,y-1)
                  checkPartOne(x - 1, y - 1,10)
                    gridItem[x - 1][y] = isCheck(x-1,y)
                  checkPartOne(x - 1, y,10)
                    gridItem[x - 1][y + 1] = isCheck(x-1,y+1)
                  checkPartOne(x - 1, y + 1,10)
                    gridItem[x][y + 1] = isCheck(x,y+1)
                  checkPartOne(x, y + 1,10)
                }
                y == gridItem[x].size - 1 -> { // right center
                    gridItem[x - 1][y] = isCheck(x-1,y)
                  checkPartOne(x - 1, y,10)
                    gridItem[x - 1][y - 1] = isCheck(x-1,y-1)
                  checkPartOne(x - 1, y - 1,10)
                    gridItem[x][y - 1] = isCheck(x,y-1)
                  checkPartOne(x, y - 1,10)
                    gridItem[x + 1][y - 1] = isCheck(x+1,y-1)
                  checkPartOne(x + 1, y - 1,10)
                    gridItem[x + 1][y] = isCheck(x+1,y)
                  checkPartOne(x + 1, y,10)
                }
                else -> {  // center
                    gridItem[x - 1][y] = isCheck(x-1,y)
                  checkPartOne(x - 1, y,10)
                    gridItem[x - 1][y - 1] = isCheck(x-1,y-1)
                  checkPartOne(x - 1, y - 1,10)
                    gridItem[x - 1][y + 1] = isCheck(x-1,y+1)
                  checkPartOne(x - 1, y + 1,10)
                    gridItem[x + 1][y] = isCheck(x+1,y)
                  checkPartOne(x + 1, y,10)
                    gridItem[x + 1][y - 1] = isCheck(x+1,y-1)
                  checkPartOne(x + 1, y - 1,10)
                    gridItem[x + 1][y + 1] = isCheck(x+1,y+1)
                  checkPartOne(x + 1, y + 1,10)
                    gridItem[x][y + 1] = isCheck(x,y+1)
                  checkPartOne(x, y + 1,10)
                    gridItem[x][y - 1] = isCheck(x,y-1)
                  checkPartOne(x, y - 1,10)
                }
            }
        }
    }
}