package com.app.adventofcode

import android.util.Log

class ReactorReboot(private val listItem : ArrayList<String>) {
private val TAG="ReactorReboot"
    val resultList= HashSet<String>()
    fun partOne(): Int {
        for (item in listItem){
            var count=0
            val values=item.contains("on")
            val dataItem=item.split(" ")[1].trim().split(",")
            var startX=0
            var endX=0
            var temp=dataItem[0].split("=")[1].trim().split("..")
            startX=temp[0].toInt()
            endX=temp[1].toInt()
            var startY=0
            var endY=0
            temp=dataItem[1].split("=")[1].trim().split("..")
            startY=temp[0].toInt()
            endY=temp[1].toInt()
            var startZ=0
            var endZ=0
            temp=dataItem[2].split("=")[1].trim().split("..")
            startZ=temp[0].toInt()
            endZ=temp[1].toInt()
            if (insideRegion(startX, endX, startY, endY, startZ, endZ))
            for (x in startX..endX){
                if(x in -50..50)
                for (y in startY..endY){
                    if(y in -50..50)
                    for (z in startZ..endZ){
                        if(z in -50..50) {
                            count++
                            if (values)
                                resultList.add("$x,$y,$z")
                            else
                                resultList.remove("$x,$y,$z")
                        }
                    }
                }
            }
        }
       return resultList.size
    }

    private fun insideRegion(startX : Int,endX: Int,startY : Int,endY : Int,startZ : Int,endZ : Int): Boolean{
        return  ((startX in -50..50) && (endX in -50..50)) || ((startY in -50..50) && (endY in -50..50)) ||((startZ in -50..50) && (endZ in -50..50))
    }
}