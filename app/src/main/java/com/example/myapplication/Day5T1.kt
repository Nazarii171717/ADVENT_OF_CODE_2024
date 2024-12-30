package com.example.myapplication

import java.io.File

fun main() {
    val filePath1 = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD5P1.txt"
    val lines1 = File(filePath1).readLines()

    val filePath2 = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD5P2.txt"
    val lines2 = File(filePath2).readLines()


    val pages = mutableListOf<Pair<Long, Long>>()
    val updates = mutableListOf<MutableList<String>>()

    for (line in lines1)
    {
        val pair = Pair(line.substring(0, line.indexOf("|")).toLong(), line.substring(line.indexOf("|") + 1).toLong())
        pages.add(pair)
    }

    for (line in lines2) {
        val oneUpdate = line.split(",").toMutableList()
        updates.add(oneUpdate)
    }

    var sum : Long = 0
    for (update in updates)
    {
        sum += checkOneUpdate(update, pages)
    }

    println(sum)

}

fun checkOneUpdate(update: MutableList<String>, pages: MutableList<Pair<Long, Long>>) : Long
{
    var i = 0
    val newUpdate = mutableListOf<Long>()
    while (i < update.size - 1)
    {
        val first = update[i].toString().toLong()
        val second = update[i + 1].toString().toLong()
        val createPair = Pair(first, second)

        if (pages.contains(createPair))
        {
            i++
            newUpdate.add(first)
            if (newUpdate.size - 1 == i)
            {
                newUpdate.add(second)
            }
        }
        else
        {
            newUpdate.clear()
            break
        }
    }
    return if (newUpdate.isNotEmpty()) newUpdate[newUpdate.size / 2] else 0L

}