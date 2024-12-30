package com.example.myapplication

import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD11.txt"
    val lines = File(filePath).readLines()

    var lineAfter25Blinks = ""
    for (line in lines)
    {
        lineAfter25Blinks = line
    }

    var i = 0
    while(i < 25)
    {
        i++
        lineAfter25Blinks = oneBlink(lineAfter25Blinks)
        //println(lineAfter25Blinks)

    }

    println(lineAfter25Blinks.split(" ").size)
}

fun oneBlink(line : String) : String
{
    val  splitter = line.split(" ")
    var lineAfterOneBlink = ""


    for (i in 0..splitter.size - 1)
    {
        if (splitter[i].toLong() == 0.toLong())
        {
            lineAfterOneBlink += "1"
        }
        else if (splitter[i].length % 2 == 0)
        {
            lineAfterOneBlink += splitter[i].substring(0, splitter[i].length / 2)
            lineAfterOneBlink += " "

            if (splitter[i].substring(splitter[i].length / 2).toInt() == 0)
            {
                lineAfterOneBlink += "0"
            }
            else
            {
                lineAfterOneBlink += splitter[i].substring(splitter[i].length / 2).toLong().toString()
            }

        }
        else
        {
            lineAfterOneBlink += (splitter[i].toLong() * 2024).toString()
        }
        lineAfterOneBlink += " "
    }
    return lineAfterOneBlink.trim()
}