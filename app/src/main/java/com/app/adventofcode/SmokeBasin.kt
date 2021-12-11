package com.app.adventofcode

import android.os.Build
import android.util.Log
import java.lang.Exception
import java.util.*

import kotlin.collections.ArrayList

class SmokeBasin(private var listItem: ArrayList<String>) {
private val TAG="SmokeBasin"
    private var gridItem=ArrayList<CharArray>()
    private var gridItemInt=ArrayList<IntArray>()
    var sumPartOne=0
    private val arrayList: ArrayList<Long> = ArrayList()
    fun init(){
        for(item in listItem){
            gridItem.add(item.toCharArray())
            gridItemInt.add(toIntArray(item.toCharArray()))
        }
    }


    private fun printArray(){
        Log.d(TAG, "init: size ${gridItemInt.size}")
        for(x in 0 until  gridItem.size) {
            for (y in gridItem[x].indices) {
                print("${gridItemInt[x][y]} ")
            }
            Log.d(TAG, "init: size ${gridItemInt[x].size}")
            println()
        }
    }

    private fun toIntArray(charArray: CharArray): IntArray{
        val result = IntArray(charArray.size)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            Arrays.setAll(result) { i -> getCharIntoInteger(charArray[i])}
        }
        return  result
    }
    fun partOne(){
        for(x in 0 until  gridItem.size){
            for(y in gridItem[x].indices){
                Log.d(TAG, "partOne: x in $x and y in $y")
                if(x==0 && y==0){   // Top left corner
                    val curr=getCharIntoInteger(gridItem[x][y])
                    val down=getCharIntoInteger(gridItem[x+1][y])
                    val right=getCharIntoInteger(gridItem[x][y+1])
                    if(curr<down && curr<right)
                        sumPartOne+=curr+1
                }else if(x==0 && y==gridItem[x].size-1){  // top right corner
                    val curr=getCharIntoInteger(gridItem[x][y])
                    val down=getCharIntoInteger(gridItem[x+1][y])
                    val left=getCharIntoInteger(gridItem[x][y-1])
                    if(curr<down && curr<left)
                        sumPartOne+=curr+1
                }else if(x==gridItem.size-1 && y==0){ // bottom left corner
                    val curr=getCharIntoInteger(gridItem[x][y])
                    val up=getCharIntoInteger(gridItem[x-1][y])
                    val left=getCharIntoInteger(gridItem[x][y+1])
                    if(curr<up && curr<left)
                        sumPartOne+=curr+1
                }else if(x==gridItem.size-1 && y==gridItem[x].size-1){ // bottom right corner
                    val curr=getCharIntoInteger(gridItem[x][y])
                    val up=getCharIntoInteger(gridItem[x-1][y])
                    val right=getCharIntoInteger(gridItem[x][y-1])
                    if(curr<up && curr<right)
                        sumPartOne+=curr+1
                }else if(x==0){ // top center
                    val curr=getCharIntoInteger(gridItem[x][y])
                    val down=getCharIntoInteger(gridItem[x+1][y])
                    val left=getCharIntoInteger(gridItem[x][y-1])
                    val right=getCharIntoInteger(gridItem[x][y+1])
                    if(curr<down && curr<left && curr<right)
                        sumPartOne+=curr+1
                }else if(y==0){ // left center
                    val curr=getCharIntoInteger(gridItem[x][y])
                    val down=getCharIntoInteger(gridItem[x+1][y])
                    val up=getCharIntoInteger(gridItem[x-1][y])
                    val right=getCharIntoInteger(gridItem[x][y+1])
                    if(curr<down && curr<up && curr<right)
                        sumPartOne+=curr+1
                }else if(x==gridItem.size-1){  // bottom center
                    val curr=getCharIntoInteger(gridItem[x][y])
                    val up=getCharIntoInteger(gridItem[x-1][y])
                    val left=getCharIntoInteger(gridItem[x][y-1])
                    val right=getCharIntoInteger(gridItem[x][y+1])
                    if(curr<up && curr<left && curr<right)
                        sumPartOne+=curr+1
                }else if(y==gridItem[x].size-1){ // left center
                    val curr=getCharIntoInteger(gridItem[x][y])
                    val down=getCharIntoInteger(gridItem[x+1][y])
                    val left=getCharIntoInteger(gridItem[x][y-1])
                    val up=getCharIntoInteger(gridItem[x-1][y])
                    if(curr<down && curr<left && curr<up)
                        sumPartOne+=curr+1
                }else{  // center
                    val curr=getCharIntoInteger(gridItem[x][y])
                    val down=getCharIntoInteger(gridItem[x+1][y])
                    val up=getCharIntoInteger(gridItem[x-1][y])
                    val left=getCharIntoInteger(gridItem[x][y-1])
                    val right=getCharIntoInteger(gridItem[x][y+1])
                    if(curr<down && curr<left && curr<right && curr<up)
                        sumPartOne+=curr+1
                }
            }
        }
        Log.d(TAG, "partOne: sum $sumPartOne")
    }

    fun partTwo(){

        for(x in 0 until  gridItemInt.size) {
            for (y in gridItemInt[x].indices) {
               if(gridItemInt[x][y]!=9 && gridItemInt[x][y]!=-1){
                   arrayList.add(0)
                   checkLocation(x,y)
               }
            }
        }
        arrayList.sort()
        Log.d(TAG, "partTwo: ${arrayList[arrayList.size-1]* arrayList[arrayList.size-2]* arrayList[arrayList.size-3]}")
    }

    private fun checkLocation(indexX: Int,indexY: Int) {
        var x=indexX
        var y=indexY
        try {
            if(indexX<0 || indexY<0 || indexX>=gridItemInt.size || indexY>=gridItemInt[indexX].size || gridItemInt[indexX][indexY]==9 || gridItemInt[indexX][indexY]==-1)
                return
            else {
                gridItemInt[indexX][indexY]=-1
                arrayList[arrayList.size-1]=arrayList[arrayList.size-1]+1
                checkLocation(indexX+1,indexY)
                checkLocation(indexX-1,indexY)
                checkLocation(indexX,indexY+1)
                checkLocation(indexX,indexY-1)
            }
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }



    private fun getCharIntoInteger(a : Char): Int{
        return Character.getNumericValue(a)
    }
}