package com.app.adventofcode

import android.util.Log

class SmokeBasin(private var listItem: ArrayList<String>) {
private val TAG="SmokeBasin"
    private var gridItem=ArrayList<CharArray>()
    var sumPartOne=0
    fun init(){
        for(item in listItem){
            gridItem.add(item.toCharArray())
        }
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

    fun getCharIntoInteger(a : Char): Int{
        return Character.getNumericValue(a)
    }
}