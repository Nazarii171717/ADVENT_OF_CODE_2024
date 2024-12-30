package com.example.myapplication

import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD4.txt"
    val lines = File(filePath).readLines()

    val matches: MutableList<String> = mutableListOf("XMAS", "SAMX")
    val rows = lines.size
    val cols = lines[0].length
    val wordSearch = Array(rows) { Array(cols) { "" } }

    for (i in 0..lines.size - 1)
    {
        for (j in 0..lines[i].length - 1)
        {
            wordSearch[i][j] = lines[i][j].toString()
        }
    }
    var count = 0

    // vertical
    for (i in 0.. wordSearch.size - 1)
    {
        var j = 0
        while (j < wordSearch[i].size - 3)
        {
            val line = wordSearch[i][j] + wordSearch[i][j + 1] + wordSearch[i][j + 2] + wordSearch[i][j + 3]

            if (matches.contains(line))
            {//println(line)
                count += 1
            }
            j += 1
        }
    }
    println()
    // horizontal
    for (i in 0..wordSearch[0].size - 1)
    {
        var j = 0
        while (j < wordSearch.size - 3)
        {
            val line = wordSearch[j][i] + wordSearch[j + 1][i] + wordSearch[j + 2][i] + wordSearch[j + 3][i]


            if (matches.contains(line))
            {// println(line)
                count += 1
            }
            j += 1
        }
    }
    println()

    // diagonal1

    for (i in 0..wordSearch.size - 4)
    {
        var j = 0
        while (j < wordSearch[i].size - 3)
        {
            val line = wordSearch[i][j] + wordSearch[i + 1][j + 1] + wordSearch[i + 2][j + 2] + wordSearch[i + 3][j + 3]

            if (matches.contains(line))
            {//println(line)
                count += 1
            }
            j += 1
        }
    }

    println()
    // diagonal2
    for (row in wordSearch) {
        row.reverse()
    }

    for (i in 0..wordSearch.size - 4)
    {
        var j = 0
        while (j < wordSearch[i].size - 3)
        {
            val line = wordSearch[i][j] + wordSearch[i + 1][j + 1] + wordSearch[i + 2][j + 2] + wordSearch[i + 3][j + 3]

            if (matches.contains(line))
            {//println(line)
                count += 1
            }
            j += 1
        }
    }

    print(count)
}