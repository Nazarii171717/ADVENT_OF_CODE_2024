package com.example.myapplication
import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD10.txt"
    val lines = File(filePath).readLines()

    val rows = lines.size
    val cols = lines[0].length
    val twoDArray: Array<Array<Int?>> = Array(rows + 2) { Array<Int?>(cols + 2) { null } }

    for (i in 1..twoDArray.size - 2) {
        for (j in 1..twoDArray[i].size - 2) {
            if (lines[i - 1][j - 1] == '.')
            {
                twoDArray[i][j] = null
            }
            else
            {
                twoDArray[i][j] = lines[i  - 1][j - 1].toString().toInt()
            }
        }
    }


    val zeros = findZeros(twoDArray)

    println(findAllTrailHeadsT2(zeros, twoDArray))

}

fun findAllTrailHeadsT2(zeros : MutableList<Pair<Int, Int>>, area : Array<Array<Int?>>): Int
{
    val all: MutableList<MutableList<MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>>> = mutableListOf()
    for (zero in zeros)
    {
        val newZero = Pair(zero, Pair(99, 99))
        all.add(findOneTrailHead(newZero, area))
    }
    val allInOneList = all.flatten().flatten().toMutableList()
    //val uniqueCoordinates: Set<Pair<Pair<Int,Int>, Pair<Int,Int>>> = allInOneList.toSet()
    return allInOneList.size
}


