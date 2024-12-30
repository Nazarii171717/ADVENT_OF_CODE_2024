package com.example.myapplication

import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD3.txt"
    val lines = File(filePath).readLines()
    var total = 0
    for (line in lines)
    {
        total += count(line)
    }

    println(total)}

fun count(line:String): Int
{
    var total = 0

    val regex = Regex("mul\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*\\)")
        // Find all matches in the current line
        val matches = regex.findAll(line)

        for (match in matches)
        {

            val num1 = match.groupValues[1].toInt()
            val num2 = match.groupValues[2].toInt()


            total += num1 * num2
        }
    return total}

