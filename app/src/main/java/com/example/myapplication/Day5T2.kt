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
        sum += checkOneUpdateT2(update, pages)
    }

    println(sum)

}

fun checkOneUpdateT2(update: MutableList<String>, pages: MutableList<Pair<Long, Long>>) : Long
{
    var isCorrect = true
    var i = 0
    while (i < update.size - 1)
    {
        val first = update[i].toLong()
        val second = update[i + 1].toLong()
        val createPair = Pair(first, second)

        if (pages.contains(createPair))
        {
            i++
        }
        else
        {
            isCorrect = false
            var temp = update[i + 1]
            update[i + 1] = update[i]
            update[i] = temp
            if (i != 0){
            i--}
        }
    }
    return if (!isCorrect) update[update.size / 2].toString().toLong() else 0L


}