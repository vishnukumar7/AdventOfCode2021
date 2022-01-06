package com.app.adventofcode

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private val TAG="MainActivity1"
    lateinit var listItem : ArrayList<String>
    lateinit var listItemBoard : ArrayList<String>
    lateinit var listItemBreakLine: ArrayList<ArrayList<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listItem = getAssetsFile("Polymerization.txt")
         val origami=Polymerization(listItemBreakLine)
        Log.d(TAG, "onCreate: "+ origami.part2())

    }

    fun getAssetsFile(fileName: String): ArrayList<String> {
        val arrList = ArrayList<String>()
        var bufferedReader: BufferedReader?=null
        try{
            bufferedReader= BufferedReader(InputStreamReader(assets.open(fileName)))
            var line:String?=null
            listItemBoard=ArrayList()
            listItemBreakLine= ArrayList()
            var wordCount = 0
            var characterCount = 0
            var paraCount = 0
            var whiteSpaceCount = 0
            var sentenceCount = 0
            var count=0;
            var sentences=""
            var itemList=ArrayList<String>()
            while (bufferedReader.readLine().also { line = it } != null) {
                if (line.equals("")) {
                    paraCount += 1
                    sentences.trim()
                    if(sentences.isNotEmpty()){
                        listItemBoard.add(sentences)
                    }
                    listItemBreakLine.add(itemList)
                    itemList= ArrayList()
                    sentences=""
                } else {
                    characterCount = characterCount.plus(line!!.length)
                    val words: List<String> = line!!.split("\\s+")
                    wordCount += words.size
                    whiteSpaceCount += wordCount - 1
                    val sentence: List<String> = line!!.split("[!?.:]+")
                    sentenceCount += sentence.size
                    arrList.add(line!!)
                    itemList.add(line!!)
                    sentences+=" "+line!!.trim()
                }

            }
            if(itemList.size>0)
                listItemBreakLine.add(itemList)
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