package com.app.adventofcode

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.text.method.ArrowKeyMovementMethod
import android.text.method.ScrollingMovementMethod
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.TextView
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    private val TAG="MainActivity1"
    lateinit var listItem : ArrayList<String>
    lateinit var listItemBoard : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listItem = getAssetsFile("Whales.txt")
        val hydrothermalVenture=TreacheryWhales(listItem)
        hydrothermalVenture.init()
        hydrothermalVenture.partOne()
        hydrothermalVenture.partTwo()
    }

    fun getAssetsFile(fileName: String): ArrayList<String> {
        val arrList = ArrayList<String>()
        var bufferedReader: BufferedReader?=null
        try{
            bufferedReader= BufferedReader(InputStreamReader(assets.open(fileName)))
            var line:String?=null
            listItemBoard=ArrayList()
            var wordCount = 0
            var characterCount = 0
            var paraCount = 0
            var whiteSpaceCount = 0
            var sentenceCount = 0
            var count=0;
            var sentences=""
            while (bufferedReader.readLine().also { line = it } != null) {
                if (line.equals("")) {
                    paraCount += 1
                    sentences.trim()
                    if(sentences.isNotEmpty()){
                        listItemBoard.add(sentences)
                    }
                    sentences=""
                } else {
                    characterCount = characterCount.plus(line!!.length)
                    val words: List<String> = line!!.split("\\s+")
                    wordCount += words.size
                    whiteSpaceCount += wordCount - 1
                    val sentence: List<String> = line!!.split("[!?.:]+")
                    sentenceCount += sentence.size
                    arrList.add(line!!)
                    sentences+=" "+line!!.trim()
                }

            }
            sentences.trim()
            if(sentences.isNotEmpty()){
                listItemBoard.add(sentences)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return arrList
    }

}