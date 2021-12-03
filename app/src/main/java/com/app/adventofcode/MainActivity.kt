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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),100)
        }
        listItem=getValues()
        val powerConsumption=PowerConsumption(listItem)
        powerConsumption.partTwo()
    }



    fun getValues() : ArrayList<String> {
        val file = File(Environment.getExternalStorageDirectory().absolutePath,"Code.txt")
        val fileInputStream = FileInputStream(file)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val arrList = ArrayList<String>()
        var line: String?
        var wordCount = 0
        var characterCount = 0
        var paraCount = 0
        var whiteSpaceCount = 0
        var sentenceCount = 0
        var count=0;
        while (bufferedReader.readLine().also { line = it } != null) {
            if (line.equals("")) {
                paraCount += 1
               // count+=getAnswered(arrList)
                    //arrList.clear()
            } else {
                characterCount = characterCount.plus(line!!.length)
                val words: List<String> = line!!.split("\\s+")
                wordCount += words.size
                whiteSpaceCount += wordCount - 1
                val sentence: List<String> = line!!.split("[!?.:]+")
                sentenceCount += sentence.size
              //  Log.d(TAG, "getValues: sentences : $sentence")
               // Log.d(TAG, "getValues: words : $words")
              //  for (k in words.indices) {
               // val code=AdventCode()
                //code.dataItem=line!!
                    arrList.add(line!!)
                //listItemAdventCode.add(code)
             //   setValue.add(line!!.trim().toLong())
               // }
            }

        }
        Log.d(TAG, "getValues: para $count")
        return arrList
    }

    private fun getImageFromExternal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                //println(getValues())
            } else {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                val uri: Uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        getImageFromExternal()
    }



}