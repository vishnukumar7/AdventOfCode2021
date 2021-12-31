package com.app.adventofcode

class SeaCucumber(private val listItem: ArrayList<String>) {
private val TAG="SeaCucumber"
    var arraysCharArray=Array(listItem.size){CharArray(listItem[0].length)}
    var tempCharArray=Array(listItem.size){CharArray(listItem[0].length)}
    var flag=true
    init {
        for((index,item) in listItem.withIndex()){
            arraysCharArray[index]=item.toCharArray()
            tempCharArray[index]=item.toCharArray()
        }
    }

    fun init(){
        for (x in tempCharArray.indices){
            for(y in tempCharArray[x].indices){
                arraysCharArray[x][y]=tempCharArray[x][y]
            }
        }
    }

    fun partOne(): Int {
        var count=0
        while(flag){
            flag=false
            moveEastForward()
            moveSouthForward()
            init()
            count++

        }
        return count
    }


    private fun moveEastForward(){
        for (x in 0 until listItem.size){
            var y=-1
            while (++y<listItem[x].length-1){
                if(arraysCharArray[x][y]=='>' && arraysCharArray[x][y+1]=='.'){
                    tempCharArray[x][y]='.'
                    tempCharArray[x][y+1]='>'
                    flag=true
                }
            }
            if(y==listItem[x].length-1 && arraysCharArray[x][y]=='>' && arraysCharArray[x][0]=='.'){
                tempCharArray[x][0]='>'
                tempCharArray[x][y]='.'
                flag=true
            }
        }
    }


    private fun moveSouthForward(){
        var x=-1
        while (++x<listItem.size-1){
           for(y in listItem[x].indices){
               if(arraysCharArray[x][y]=='v' && tempCharArray[x+1][y]=='.'){
                   tempCharArray[x][y]='.'
                   tempCharArray[x+1][y]='v'
                   flag=true
               }
           }
        }
        for(y in listItem[x].indices){
            if(arraysCharArray[x][y]=='v' && tempCharArray[0][y]=='.' && arraysCharArray[0][y]!='v'){
                tempCharArray[x][y]='.'
                tempCharArray[0][y]='v'
                flag=true
            }
        }
    }
}