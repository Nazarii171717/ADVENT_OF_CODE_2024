package com.example.myapplication

import java.io.File

fun main()
{
    val lines = readFile("C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD1.txt")

    val leftColumn = mutableListOf<Int>() // Creates an empty mutable list of integers
    val rightColumn = mutableListOf<Int>() // Creates another empty mutable list of integers

    for (line in lines)
    {
        leftColumn.add(line.substring(0, line.indexOf("   ")).toInt())
        rightColumn.add(line.substring(line.lastIndexOf(" ") + 1).toInt())
    }

    leftColumn.sort()
    rightColumn.sort()
    var sumOfDifferences = 0
    for (i in 0..leftColumn.size - 1)
    {
        sumOfDifferences += Math.abs(rightColumn[i] - leftColumn[i])
    }

    print(sumOfDifferences)
}

fun readFile(filePath: String) : List<String>
{
    val lines = File(filePath).readLines()
    return lines
}