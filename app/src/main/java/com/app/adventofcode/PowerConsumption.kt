package com.app.adventofcode

import android.icu.text.LocaleDisplayNames
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