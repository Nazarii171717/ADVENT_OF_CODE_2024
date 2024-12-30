package com.example.myapplication

import java.io.File

fun main() {
    val filePath =
        "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD4.txt"
    val lines = File(filePath).readLines()

    val matches: MutableList<String> = mutableListOf("MAS", "SAM")
    val rows = lines.size
    val cols = lines[0].length
    val wordSearch = Array(rows) { Array(cols) { "" } }

    for (i in 0..lines.size - 1) {
        for (j in 0..lines[i].length - 1) {
            wordSearch[i][j] = lines[i][j].toString()
        }
    }

    var count = 0
    for (i in 1..wordSearch.size - 2)
    {
        var j = 1
        while (j < wordSearch[i].size - 1)
        {
            val line1 = wordSearch[i - 1][j - 1] + wordSearch[i][j] + wordSearch[i + 1][j + 1]
            val line2 = wordSearch[i - 1][j + 1] + wordSearch[i][j] + wordSearch[i + 1][j - 1]
            if (matches.contains(line1) && matches.contains(line2))
            {
                count += 1
            }
            j += 1
        }
    }

    println(count)
}