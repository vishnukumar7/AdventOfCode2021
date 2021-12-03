package com.app.adventofcode

import android.util.Log

class PowerConsumption(private var listItem: ArrayList<String>) {

    private val TAG: String = "PowerConsumption"

    fun partOne(){
        var gammaRate=""
        var eplisonRate=""
        var gamma=0
        var eplison=0
        val column=listItem[0].length
        var pos=0
        while (pos<column){
            var zeros=0
            var ones=0
            for(item in listItem){
                if(item[pos]=='0')
                    zeros++
                else
                    ones++

            }

            if(zeros>ones){
                gammaRate+="0"
                eplisonRate+="1"
            }else{
                gammaRate+="1"
                eplisonRate+="0"
            }
            pos++
        }

        eplison=convertBinaryToDecimal(eplisonRate.toLong())
        gamma=convertBinaryToDecimal(gammaRate.toLong())

        Log.d(TAG, "powerConsumption: $eplison $gamma ${eplison*gamma} ")
    }

    fun partTwo(){
        Log.d(TAG, "partTwo: ${oxygenGeneratorRating() * co2ScrubberRating()}")
    }


    fun oxygenGeneratorRating(): Int{
        val ratingList=ArrayList<String>(listItem)
        val column=ratingList[0].length
        var pos=0
        Log.d(TAG, "oxygenGeneratorRating: $ratingList")
        while (pos<column){
            var zeros=0
            var ones=0
            var zeroesList:ArrayList<String> = ArrayList()
            var onesList:ArrayList<String> = ArrayList()
            for(item in ratingList){
                if(item[pos]=='0') {
                    zeros++
                    zeroesList.add(item)
                }
                else {
                    ones++
                    onesList.add(item)
                }
            }

            if(zeros>ones){
                ratingList.clear()
                ratingList.addAll(zeroesList)
            }else{
                ratingList.clear()
                ratingList.addAll(onesList)
            }
            pos++
            Log.d(TAG, "oxygenGeneratorRating: $ratingList $pos")
            if(ratingList.size==2 || ratingList.size==1)
                break
        }

        if (ratingList.size != 1) {
            while (pos<column){
                if(ratingList[0][pos]==ratingList[1][pos]){

                }else if (ratingList[0][pos]=='1')
                    return convertBinaryToDecimal(ratingList[0].toLong())
                else return convertBinaryToDecimal(ratingList[1].toLong())
            }
        }
            return convertBinaryToDecimal(ratingList[0].toLong())
    }

    fun co2ScrubberRating(): Int{
        val ratingList=ArrayList<String>(listItem)
        val column=ratingList[0].length
        var pos=0
        Log.d(TAG, "co2ScrubberRating: $ratingList")
        while (pos<column){
            var zeros=0
            var ones=0
            val zeroesList:ArrayList<String> = ArrayList()
            val onesList:ArrayList<String> = ArrayList()
            for(item in ratingList){
                if(item[pos]=='0') {
                    zeros++
                    zeroesList.add(item)
                }
                else {
                    ones++
                    onesList.add(item)
                }
            }

            if(zeros>ones){
                ratingList.clear()
                ratingList.addAll(onesList)
            }else{
                ratingList.clear()
                ratingList.addAll(zeroesList)
            }
            pos++
            Log.d(TAG, "co2ScrubberRating: $ratingList $pos")
            if(ratingList.size==2 || ratingList.size==1)
                break
        }

        if (ratingList.size != 1) {
            while (pos<column){
                when {
                    ratingList[0][pos]==ratingList[1][pos] -> {

                    }
                    ratingList[0][pos]=='0' -> return convertBinaryToDecimal(ratingList[0].toLong())
                    else -> return convertBinaryToDecimal(ratingList[1].toLong())
                }
            }
        }
            return convertBinaryToDecimal(ratingList[0].toLong())
    }


    fun convertBinaryToDecimal(num1: Long): Int {
        var num=num1
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }
}