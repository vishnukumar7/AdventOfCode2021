package com.app.adventofcode

import android.util.Log
import com.app.adventofcode.utils.ChitonModel

class Chiton(private val listItem: ArrayList<String>) {
    private val TAG="Chiton"
    var gridItem= Array(listItem.size){IntArray(listItem[0].length)}
    private var gridItemModel= Array(listItem.size){Array(listItem[0].length){ChitonModel()} }
    var smaller=686L

    fun partOne(){
        for (x in 0 until  listItem.size){
            for(y in listItem[x].indices){
                gridItem[x][y]=Character.getNumericValue(listItem[x][y])
                gridItemModel[x][x]=ChitonModel(data = gridItem[x][y])
            }
        }
        gridItemModel[0][0].current=0
        printArray1()
        rescursion1(0,0)

        Log.d(TAG, "partOne: values ${gridItemModel[listItem.size-1][listItem[0].length-1].current}")
    }

    fun rescursion(x: Int,y: Int,data: Int){
        if(x==listItem.size)
            return
        if (y==listItem[x].length)
            return
        if(smaller<data)
            return
        if(gridItem[x][y]==9)
            return
        if(x==listItem.size-1 && y==listItem[x].length-1){
            if(smaller>data){
                smaller= data.toLong()
            }
            Log.d(TAG, "rescursion: smaller $smaller")
        }
        rescursion(x+1,y,data+gridItem[x][y])
        rescursion(x,y+1,data+gridItem[x][y])
    }

    private fun printArray(){
        Log.d(TAG, "printArray: ")
        for (x in gridItemModel.indices){
            for(y in gridItemModel[x].indices){
                print("${gridItemModel[x][y].current} ")
            }
            println()
        }
    }
    private fun printArray1(){
        Log.d(TAG, "printArray: ")
        for (x in gridItemModel.indices){
            for(y in gridItemModel[x].indices){
                print("${gridItem[x][y]} ")
            }
            println()
        }
    }

    private fun rescursion1(x: Int, y: Int){
        if(x>=listItem.size-1)
            return
        if (y>=listItem[x].length-1)
            return
        if(x==listItem.size-1 && y==listItem[x].length-1){
            return
        }
        for (row in x until listItem.size){
            val data=gridItemModel[row][y]
            val next=gridItemModel[row][y+1]
            if(row!=listItem.size-1) {
                val down = gridItemModel[row + 1][y]
                val sum=data.current+gridItem[row + 1][y]
                if(down.firstTime) {
                    down.current = sum
                    down.firstTime=false
                }else if(down.current>sum){
                    down.current= sum
                }
                gridItemModel[row+1][y]=down
            }
            val sum=data.current+gridItem[row][y+1]
            if(next.firstTime) {
                next.current = sum
                next.firstTime=false
            }else if(next.current>sum){
                next.current= sum
            }
            gridItemModel[row][y+1]=next
        }

        for (col in y until listItem[0].length){
            val data=gridItemModel[x][col]
            if(col!=listItem[0].length-1){
                val next=gridItemModel[x][col+1]
                val sum=data.current+gridItem[x][col+1]
                if(next.firstTime) {
                    next.current = sum
                    next.firstTime=false
                }else if(next.current>sum){
                    next.current= sum
                }
                gridItemModel[x][col+1]=next
            }
            val next=gridItemModel[x+1][col]
            val sum=data.current+gridItem[x+1][col]
            if(next.firstTime) {
                next.current = sum
                next.firstTime=false
            }else if(next.current>sum){
                next.current= sum
            }
            gridItemModel[x+1][col]=next
        }
        if(x>80)
        printArray()
        rescursion1(x+1,y+1)
    }


}