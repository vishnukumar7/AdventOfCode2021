package com.app.adventofcode

import android.util.Log

class GiantSquid(private var boardList: ArrayList<String>) {

    private val TAG= "GiantSquid"
    private val gridBoard: ArrayList<Array<IntArray>> = ArrayList()
    private val listItem= arrayListOf(84,28,29,75,58,71,26,6,73,74,41,39,87,37,16,79,55,60,62,80,64,95,46,
        15,5,47,2,35,32,78,89,90,96,33,4,69,42,30,54,85,65,83,44,63,20,17,66,81,67,77,36,68,82,93,10,
        25,9,34,24,72,91,88,11,38,3,45,14,56,22,61,97,27,12,48,18,1,31,98,86,19,99,92,8,43,52,23,21,0,7,50,57,70,49,13,51,40,76,94,53,59)

    fun init(){
        for (board in boardList){

            val tempboard=board.replace("  "," ").trim()
          //  Log.d(TAG, "init: $tempboard")
            val arrays=tempboard.split(" ")
          //  Log.d(TAG, "init: $arrays")
            val array2d=Array(5){ IntArray(5)}
            var pos=0
            for (x in 0 until 5){
                for (y in 0 until 5){
                    array2d[x][y]=arrays[pos].toInt()
                    pos++
                }
            }
            gridBoard.add(array2d)
        }
    }

    fun partOne(): Boolean {
        for(data in listItem){
            for(board in gridBoard){
                var flag=false
                for (x in 0 until 5){
                    for (y in 0 until 5){
                        if(board[x][y]==data.toInt()){
                            board[x][y]=-1
                            flag=true
                            break
                        }
                    }
                    if(flag)
                        break
                }
            }
            if(checkWhoWins(data)) return true
        }
        return false
    }

    fun partTwo(): Boolean {
        for(data in listItem){
            for(board in gridBoard){
                var flag=false
                for (x in 0 until 5){
                    for (y in 0 until 5){
                        if(board[x][y]==data.toInt()){
                            board[x][y]=-1
                            flag=true
                            break
                        }
                    }
                    if(flag)
                        break
                }

            }
           if ( checkLastWin(data)) return true

        }
        return false
    }

    fun printBoard(boardList: Array<IntArray>){
        for (x in 0 until 5){
            for (y in 0 until 5){
                print("${boardList[x][y]} ")
            }
            println()
        }
    }

    fun checkLastWin(bingo: Int): Boolean {
        if(gridBoard.size==1){
            Log.d(TAG, "checkLastWin: ")
            printBoard(gridBoard[0])
           if(checkWhoWins(bingo))
            return true
        }else{
            var index=0
            while(index<gridBoard.size){
                val board=gridBoard[index]
                for (x in 0 until 5){
                    when {
                        board[x][0]==-1 && board[x][1]==-1 && board[x][2]==-1 && board[x][3]==-1 && board[x][4]==-1 -> {
                            gridBoard.remove(gridBoard[index])
                            index--
                            break
                        }
                        board[0][x]==-1 && board[1][x]==-1 && board[2][x]==-1 && board[3][x]==-1 && board[4][x]==-1 -> {
                            gridBoard.remove(gridBoard[index])
                            index--
                            break
                        }
                    }
                }
                index++
            }
        }
        return false
    }

    fun checkWhoWins(bingo: Int): Boolean {
        for(board in gridBoard){

            for (x in 0 until 5){
                when {
                    board[x][0]==-1 && board[x][1]==-1 && board[x][2]==-1 && board[x][3]==-1 && board[x][4]==-1 -> {
                        winBoard(board,bingo)
                        return true
                    }
                    board[0][x]==-1 && board[1][x]==-1 && board[2][x]==-1 && board[3][x]==-1 && board[4][x]==-1 -> {
                        winBoard(board,bingo)
                        return true
                    }
                }
            }
        }
        return false
    }

    fun winBoard(boardList: Array<IntArray>,bingo: Int){
        var sum=0
        for (x in 0 until 5){
            for (y in 0 until 5){
                print("${boardList[x][y]} ")
               if(boardList[x][y]!=(-1)){
                   sum+=boardList[x][y]
               }
                println()
            }

        }
        Log.d(TAG, "winBoard: $sum")
        Log.d(TAG, "winBoard: $bingo")
        Log.d(TAG, "winBoard: ${sum * bingo}")
    }


}