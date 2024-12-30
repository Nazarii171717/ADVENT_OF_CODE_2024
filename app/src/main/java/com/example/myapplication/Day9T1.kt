package com.example.myapplication

import java.io.File
import java.util.Arrays

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\inputD9.txt"
    val lines = File(filePath).readLines()

    for (line in lines)
    {
        println(calculateSum(compatFile(convertToIndividualBlocks(line))))
        //println((compatFile(convertToIndividualBlocks(line))))
    }
}

fun convertToIndividualBlocks(line : String) : MutableList<String>
{
    val list = mutableListOf<String>()
    val arrayOfLine = line.trim().toCharArray().map { it.toString() }.toTypedArray()

    var num = 0
    var point = "."
    var numOrPoint = true // checks the order in which we should add point or number
    for (i in 0 until arrayOfLine.size)
    {
        var k = 0
        while (k < arrayOfLine[i].toInt())
        {
            if (numOrPoint)
            {
                list.add(num.toString())
            }
            else
            {
                list.add(point)
            }
            k += 1
        }
        if (numOrPoint)
        {
            numOrPoint = false
            num += 1
        }
        else
        {
            numOrPoint = true
        }
    }
    return list
}

fun compatFile(file : MutableList<String>) : MutableList<String>
{
    var j = 0
    for (i in file.size - 1 downTo 0)
    {
        if (!file[i].equals("."))
        {
            while (!file[j].equals("."))
            {
                j += 1
            }
            file[j] = file[i]
            file[i] = "."
            if (j >= i){break}
        }
    }

    file.removeAt(file.indexOf("."))
    return file.filterNot { it.contains(".") } as MutableList<String>

}

fun calculateSum(file : MutableList<String>): Long {
    var sum : Long = 0
    for (i in 0..file.size - 1)
    {
        sum += i * file[i].toInt()
    }

    return sum
}
//00...111...2...333.44.5555.6666.777.888899
//0099811188827773336446555566