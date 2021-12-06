package com.app.adventofcode

import android.util.Log
import kotlin.math.log

class Lanternfish() {
 private val TAG="Lanternfish"
    private var listItem: ArrayList<String> = ArrayList()
   // private var currentItem: String="1,1,1,1,1,5,1,1,1,5,1,1,3,1,5,1,4,1,5,1,2,5,1,1,1,1,3,1,4,5,1,1,2,1,1,1,2,4,3,2,1,1,2,1,5,4,4,1,4,1,1,1,4,1,3,1,1,1,2,1,1,1,1,1,1,1,5,4,4,2,4,5,2,1,5,3,1,3,3,1,1,5,4,1,1,3,5,1,1,1,4,4,2,4,1,1,4,1,1,2,1,1,1,2,1,5,2,5,1,1,1,4,1,2,1,1,1,2,2,1,3,1,4,4,1,1,3,1,4,1,1,1,2,5,5,1,4,1,4,4,1,4,1,2,4,1,1,4,1,3,4,4,1,1,5,3,1,1,5,1,3,4,2,1,3,1,3,1,1,1,1,1,1,1,1,1,4,5,1,1,1,1,3,1,1,5,1,1,4,1,1,3,1,1,5,2,1,4,4,1,4,1,2,1,1,1,1,2,1,4,1,1,2,5,1,4,4,1,1,1,4,1,1,1,5,3,1,4,1,4,1,1,3,5,3,5,5,5,1,5,1,1,1,1,1,1,1,1,2,3,3,3,3,4,2,1,1,4,5,3,1,1,5,5,1,1,2,1,4,1,3,5,1,1,1,5,2,2,1,4,2,1,1,4,1,3,1,1,1,3,1,5,1,5,1,1,4,1,2,1"
    private var currentItem: String="3,4,3,1,2"
    private var listItem1: String="0,1,2,3,4,5,6,7,8"
    private var hasMapValue:HashMap<String,String> = HashMap()
    private var hasMapItem:HashMap<String,String> = HashMap()

    fun init(){
        val arrays=currentItem.split(",")
        var pos=0
        if(arrays.size>10){
          while (pos<arrays.size){
              var newItem=arrays[pos]
              pos++
              var x=0
              while (x<5 && pos<arrays.size){
                  newItem+=",${arrays[pos]}"
                  pos++
                  x++
              }
              listItem.add(newItem)
          }
        }else
            listItem.add(currentItem)

    }

    fun init2(){
        var item=listItem1.split(",")
        for (data in item){
            hasMapValue[data]=sumValue(data).toString()
            hasMapItem[data]=itemValue(data)
        }
    }

    fun partTwo3(){
        var sum: Long=0
        for (item in 0 until 15){
            val arrays=currentItem.split(",") as ArrayList<String>
            currentItem=""
            for(dataArrays in arrays){
                if(currentItem.isEmpty())
                    currentItem=hasMapItem[dataArrays]!!
                else
                    currentItem+=","+hasMapItem[dataArrays]!!
            }
            Log.d(TAG, "partTwo3: $item round of 3")
        }
        val array=currentItem.split(",") as ArrayList<String>
        for(dataArray in array){
            sum+=hasMapValue[dataArray]!!.toLong()
        }
        Log.d(TAG, "partTwo3: sum $sum")

    }



    fun partTwo2(){
        val listItem=currentItem.split(",") as ArrayList<String>
        val dataHash=HashMap<String,String>()
        var sum: Long=0
        for(item in listItem){
            if(dataHash.contains(item)){
                sum+= dataHash[item]!!.toLong()
            }else{
                val data:Long=sumValue(item)
                sum+=data
                dataHash[item] = data.toString()
            }
        }
        Log.d(TAG, "partTwo2: sum : $sum")
    }

    fun partOne(){
      //  Log.d(TAG, "partOne: $currentItem")
        var sum=0
        for(newData in listItem){
            var currentItem=newData
            for (data in 0 until 16){
                currentItem.trim()
                var newFish=0
                var newItem=""
                val arrayData=currentItem.split(",")
                for(values in arrayData){
                    var item=values.toInt()
                    if(item==0) {
                        newFish++
                        if(newItem.isEmpty())
                            newItem="6"
                        else
                            newItem+=",6"
                    }
                    else{
                        item--
                        if(newItem.isEmpty())
                            newItem="$item"
                        else
                            newItem+= ",$item"
                    }
                }
                for (x in 0 until newFish){
                    newItem+=",8"
                }
                currentItem=newItem
                // Log.d(TAG, "partOne:$data $currentItem")
            }
            sum+=currentItem.split(",").size
            Log.d(TAG, "partOne: total list : ${currentItem.split(",").size}")
        }
        Log.d(TAG, "partOne: list total : $sum")
    }

    fun partTwo(){
        var round=0
        while (round<15){
            var newList=currentItem.split(",") as ArrayList<String>
         //   Log.d(TAG, "partTwo: current item  $currentItem")
            newFish(newList)
            round++
            Log.d(TAG, "partTwo: $round round of 16")
        }
        var newList=currentItem.split(",") as ArrayList<String>
        round++
        Log.d(TAG, "partTwo: $round round of 16 : ${newFish(newList)}")
    }

    fun sumValue(data: String): Long {
        var itemData=data
        var fishNew=0
        var size=0

        for (dataValues in 0 until 16){
            itemData.trim()
            var newFish=0
            var newItem=""
            val arrayData=itemData.split(",")
            for(values in arrayData){
                var item=values.toInt()
                if(item==0) {
                    newFish++
                    fishNew++
                    if(newItem.isEmpty())
                        newItem="6"
                    else
                        newItem+=",6"
                }
                else{
                    item--
                    if(newItem.isEmpty())
                        newItem="$item"
                    else
                        newItem+= ",$item"
                }
            }
            for (x in 0 until newFish){
                newItem+=",8"
            }
            itemData=newItem
            size++
            if(size%40==0)
            Log.d(TAG, "partTwo: total list $size size of 256 : $data ${itemData.split(",").size}")
            //   Log.d(TAG, "partTwo: next current $itemData")
        }
        Log.d(TAG, "sumValue: $itemData")
        return itemData.split(",").size.toLong()
    }

    fun itemValue(data: String): String {
        var itemData=data
        var fishNew=0
        var size=0

        for (dataValues in 0 until 16){
            itemData.trim()
            var newFish=0
            var newItem=""
            val arrayData=itemData.split(",")
            for(values in arrayData){
                var item=values.toInt()
                if(item==0) {
                    newFish++
                    fishNew++
                    if(newItem.isEmpty())
                        newItem="6"
                    else
                        newItem+=",6"
                }
                else{
                    item--
                    if(newItem.isEmpty())
                        newItem="$item"
                    else
                        newItem+= ",$item"
                }
            }
            for (x in 0 until newFish){
                newItem+=",8"
            }
            itemData=newItem
            size++
            if(size%64==0)
                Log.d(TAG, "partTwo: total list $size size of 256 : $data ${itemData.split(",").size}")
            //   Log.d(TAG, "partTwo: next current $itemData")
        }
        Log.d(TAG, "sumValue: $itemData")
        return itemData
    }

    fun newFish(list : ArrayList<String>): Long{
        var sum: Long=0
        var size=0
        currentItem=""
        for(newData in list){
            var itemData=newData
            for (data in 0 until 16){
                itemData.trim()
                var newFish=0
                var newItem=""
                val arrayData=itemData.split(",")
                for(values in arrayData){
                    var item=values.toInt()
                    if(item==0) {
                        newFish++
                        if(newItem.isEmpty())
                            newItem="6"
                        else
                            newItem+=",6"
                    }
                    else{
                        item--
                        if(newItem.isEmpty())
                            newItem="$item"
                        else
                            newItem+= ",$item"
                    }
                }
                for (x in 0 until newFish){
                    newItem+=",8"
                }
                itemData=newItem
              //   Log.d(TAG, "partTwo: next current $itemData")
            }
           if(currentItem.isEmpty())
               currentItem=itemData
            else
               currentItem+=",$itemData"
            sum+=itemData.split(",").size.toLong()
            Log.d(TAG, "partTwo: total list $size size of ${list.size} : ${itemData.split(",").size}")
            size++
        }
        Log.d(TAG, "partTwo: list total : $sum")
        return sum
    }
}