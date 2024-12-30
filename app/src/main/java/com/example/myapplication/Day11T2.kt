

package com.example.myapplication

import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD11.txt"
    val lines = File(filePath).readLines()

    var numbers = mutableMapOf<String, Long>()
    for (line in lines)
    {
        val newLine = line.split(" ")
        for (num in newLine)
        {
            if (!numbers.contains(num))
            {
                numbers[num] = 1
            }
            else
            {
                numbers[num] = numbers[num].toString().toLong() + 1
            }
        }
    }
    var i = 0
    while(i < 75)
    {
        i++
        numbers = oneBlinkT2(numbers)
    }

    var sum : Long = 0
    for (num in numbers.keys)
    {
        sum += numbers[num].toString().toLong()
    }
    println(sum)
}

fun oneBlinkT2(numbers: MutableMap<String, Long>): MutableMap<String, Long> {
    val numbers2 = mutableMapOf<String, Long>()

    for ((key, value) in numbers) {

        //1
        if (key.toLong() == 0L)
        {
            numbers2["1"] = numbers2.getOrDefault("1", 0) + value
        }
        //2
        else if (key.length % 2 == 0)
        {
            val mid = key.length / 2
            val left = key.substring(0, mid).toLong()
            val right = key.substring(mid).toLong()
            numbers2[left.toString()] = numbers2.getOrDefault(left.toString(), 0) + value
            numbers2[right.toString()] = numbers2.getOrDefault(right.toString(), 0) + value
        }
        //3
        else
        {
            val newKey = (key.toLong() * 2024).toString()
            numbers2[newKey] = numbers2.getOrDefault(newKey, 0) + value
        }
    }
    return numbers2.filterValues { it != 0L }.toMutableMap()
}
