package com.app.adventofcode

import android.util.Log
import kotlin.math.pow

class PacketDecoder(private val listItem: ArrayList<String>) {
    private val TAG = "PacketDecoder"
    var sum = 0L
    var packetVersionType = 0

    //var map=HashMap<String,String>();
    var arrayList = ArrayList<String>()

    fun partOne(): Long {
        val binary = hexadecimalIntoBinary(listItem[0])
        packet(binary)
        Log.d(TAG, "partOne: $sum")
        return sum
    }

    fun partTwo() {
        for (item in listItem) {
            val binary = hexadecimalIntoBinary(item)
            Log.d(TAG, "packet: binary start $binary")
            packet(binary)
            Log.d(TAG, "partTwo: $item ==> $arrayList")
            transmissionPacket(arrayList)
            arrayList.clear()

        }
    }


    private fun toDecimal(binary: String): Long {
        var data = binary.toLong()
        var index = 0
        var decimal = 0L
        while (data != 0L) {
            val rem = data % 10
            if (rem == 1L)
                decimal += 2.0.pow(index.toDouble()).toLong()
            data /= 10
            index++
        }
        return decimal

    }

    private fun hexadecimalIntoBinary(hexadecimalN: String): String {
        var i = 0
        var binaryNum = ""
        while (i < hexadecimalN.length) {
            when (hexadecimalN[i]) {
                '0' -> binaryNum += "0000"
                '1' -> binaryNum += "0001"
                '2' -> binaryNum += "0010"
                '3' -> binaryNum += "0011"
                '4' -> binaryNum += "0100"
                '5' -> binaryNum += "0101"
                '6' -> binaryNum += "0110"
                '7' -> binaryNum += "0111"
                '8' -> binaryNum += "1000"
                '9' -> binaryNum += "1001"
                'A', 'a' -> binaryNum += "1010"
                'B', 'b' -> binaryNum += "1011"
                'C', 'c' -> binaryNum += "1100"
                'D', 'd' -> binaryNum += "1101"
                'E', 'e' -> binaryNum += "1110"
                'F', 'f' -> binaryNum += "1111"
            }
            i++
        }
        return binaryNum
    }

    private fun packet(temp: String): String {
        var binary = temp
         // Log.d(TAG, "packet: binary start $binary")
        if (binary.contains("1")) {
            val packetVersion = toDecimal(binary.substring(0, 3))
             Log.d(TAG, "packet: packetVersion $packetVersion")
            sum += packetVersion
            packetVersionType++

            val packetType = toDecimal(binary.substring(3, 6))
            Log.d(TAG, "packet: packet type  $packetType")
            if (packetType == 4L) {
                binary = binary.substring(6)
                return computePacket(binary)
            } else {
                arrayList.add("packetVersion $packetType")
                binary = binary.substring(6)
                return processPacket(binary)
            }
        }
        return binary
    }

    private fun processPacket(temp: String): String {
        var binary = temp
        //Log.d(TAG, "processPacket: binary start $binary")
        if (binary[0] == '1') {
            //   Log.d(TAG, "processPacket: type id 1")
            val tempType1 = binary.substring(1, 12)
            Log.d(TAG, "processPacket: type 1 length of the bits $tempType1")
            var lengthOfTheType = toDecimal(tempType1)
            binary = binary.substring(12)
             Log.d(TAG, "processPacket: length of the type $lengthOfTheType")
            while (lengthOfTheType-- != 0L) {
                binary = packet(binary)
            }
            return binary
            // binary
        } else {
            //  Log.d(TAG, "processPacket: type id 0")
            val tempType0 = binary.substring(1, 16)
            Log.d(TAG, "processPacket: type 0 length of the bits $tempType0")
            //  Log.d(TAG, "processPacket: type 0 binary : $tempType0")
            val lengthOfTheType = toDecimal(tempType0)
            Log.d(TAG, "processPacket: type 0 length of the type $lengthOfTheType")
            //  Log.d(TAG, "processPacket: type 0 length type : $lengthOfTheType")
            var lengthType = binary.substring(16, 16 + lengthOfTheType.toInt())
            while (lengthType.contains("1")) {
                lengthType = packet(lengthType)
            }
            return binary.substring(16 + lengthOfTheType.toInt())
        }
    }

    private fun computePacket(temp: String): String {
        var binary = temp
        // Log.d(TAG, "computePacket: binary start : $binary")
        if (binary.contains("1")) {
            var prefix = '-'
            while (prefix != '0') {
                prefix = binary[0]
                val values = toDecimal(binary.substring(1, 5))
                arrayList.add("Values $values")
                binary = binary.substring(5)
            }
            if (binary.isNotEmpty()) {
                return packet(binary)
            }
        }

        return binary
    }

    private fun calculate(packetVersion: Long, values: Long, totalValues: Long): Long {
        when (packetVersion) {
            0L -> {
                return totalValues + values
            }
            1L -> {
                return totalValues * values
            }
            2L -> {
                return if (totalValues < values)
                    totalValues
                else values
            }
            3L -> {
                return if (totalValues < values)
                    values
                else totalValues
            }
            5L -> {
                return if (totalValues < values)
                    0
                else 1
            }
            6L-> {
                return if (totalValues > values)
                    0
                else 1
            }
             7L->{
                 return if (totalValues == values)
                     1
                 else 0
             }

        }
        return 0
    }


    private fun transmissionPacket(arrayList: ArrayList<String>) {
        ArrayList<String>()
        for (index in arrayList.size-2 downTo 0) {
            if (arrayList[index].contains("packetVersion") && arrayList[index + 1].contains("Values")) {
                val packetVersion = arrayList[index].split(" ")[1]
                var values = 0L
                var valuesIndex = index + 1
                while (valuesIndex < arrayList.size && arrayList[valuesIndex].contains("Values")) {
                    values = if(values==0L){
                        arrayList[valuesIndex].split(" ")[1].toLong()
                    }else{
                        calculate(
                            packetVersion.toLong(),
                            arrayList[valuesIndex].split(" ")[1].toLong(),
                            values
                        )
                    }
                    arrayList[valuesIndex]="-1"
                    valuesIndex++
                }
                arrayList[index]="Values $values"
                break
            }
        }
        arrayList.removeIf { it == "-1" }
        Log.d(TAG, "transmissionPacket: $arrayList")
        if (arrayList.size != 1)
            transmissionPacket(arrayList)

    }


}