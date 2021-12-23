package com.app.adventofcode

import android.util.Log

class TransparentOrigami(private var listItem: ArrayList<ArrayList<String>>) {
private val TAG="TransparentOrigami"
    var gridItem: Array<CharArray> =Array(10) { CharArray(10)}
    var rows=1
    var columns=1
    var dotVisible=0
    var duplicateVisible=0
    init {
        val rowList=ArrayList<String>()
        val colList= ArrayList<String>()
        for (item in listItem[0]){
    //        Log.d(TAG, ":init  $item")
            val dataSplit=item.trim().split(",")
            if(rows<dataSplit[1].toInt()){
                rows=dataSplit[1].toInt()
            }
            if(columns<dataSplit[0].toInt()){
                columns=dataSplit[0].toInt()
            }
            rowList.add(dataSplit[1])
            colList.add(dataSplit[0])
        }
        rows++
        columns++
        Log.d(TAG, "init : $rows $columns")
        gridItem= Array(rows){ CharArray(columns) }
        for (x in 0 until rows){
            for(y in 0 until columns){
                gridItem[x][y]='.'
            }
        }
        for (index in 0 until rowList.size){
            gridItem[rowList[index].toInt()][colList[index].toInt()]='#'
        }
    }

    fun partOne1(){
        for(item in listItem[0]){
            val itemValues=item.trim().split(",")
            var flag=true
            for(dataItem in listItem[0]){
                val dataItemValues=dataItem.trim().split(",")
                if(item!=dataItem){
                    if(itemValues[0]==dataItemValues[1] && itemValues[1]==dataItemValues[0]){
                        flag=false
                        duplicateVisible++
                        break
                    }
                }
            }
            if(flag)
                dotVisible++
        }
        Log.d(TAG, "partOne: $dotVisible $duplicateVisible")
        var count=dotVisible+(duplicateVisible/2)
        Log.d(TAG, "partOne: count $count")
    }



    fun partOne(){
     //   printArray()
        for(item in listItem[1]){
            val data=item.trim().split(" ")
            val values=data[data.size-1].trim().split("=")
            if(values[0].trim()=="y"){
                horizontalFold(values[1].toInt())
                break
            }else if(values[0].trim()=="x"){
                verticalFold(values[1].toInt())
                break
            }
            printArray()
        }

      /*  var dotCount=0
        for(x in 0 until rows){
            for (y in 0 until columns){
                if(gridItem[x][y]=='.')
                    dotCount++
            }
        }*/
        Log.d(TAG, "partOne: count : $dotVisible")
    }
    private fun printArray(){
        Log.d(TAG, "printArray: ")
        for(x in 0 until rows){
            for (y in 0 until columns){
                print("${gridItem[x][y]} ")
            }
            println()
        }
    }

    private fun horizontalFold(index: Int){
        var newGridItem=Array(index){ CharArray(columns)}
        for(x in 0 until index){
            for (y in 0 until columns){
                if (gridItem[rows-1-x][y] != gridItem[x][y]) {
                    dotVisible++
                }

                if (gridItem[rows-1-x][y]=='#' || gridItem[x][y]=='#'){
                    newGridItem[x][y]='#'
                }else{
                    newGridItem[x][y]='.'
                }
            }
        }
        gridItem=newGridItem
        rows=index
    }

    private fun verticalFold(index: Int){
        val newGridItem=Array(rows){ CharArray(index)}
        for(x in 0 until rows){
            for (y in 0 until index){
                if (gridItem[x][columns-1-y] != gridItem[x][y]) dotVisible++

                if (gridItem[x][columns-1-y]=='#' || gridItem[x][y]=='#'){
                    newGridItem[x][y]='#'

                }else{
                    newGridItem[x][y]='.'
                }
            }
        }
        gridItem=newGridItem
        columns=index
    }


}