package com.example.myapplication

import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD12.txt"
    val lines = File(filePath).readLines()

    val rows = lines.size + 2
    val cols = lines[0].length + 2
    val territory = Array(rows) { Array(cols) { "." } }

    for (i in 1..rows - 2) {
        for (j in 1..cols - 2) {
            territory[i][j] = lines[i - 1][j - 1].toString()
        }
    }
    val allRegions = findAllRegions(territory)

    println(calculateTotalSidesT2(allRegions, territory))

}

fun calculateSidesForOne(territory : Array<Array<String>>, region: MutableList<Pair<Int, Int>>) : Int
{
    region.sortWith(compareBy({ it.first }, { it.second }))
    var sides = 0
    for (pair in region)
    {
        val plant = territory[pair.first][pair.second]
        //left
        if (plant != territory[pair.first - 1][pair.second] && plant != territory[pair.first][pair.second - 1])
        {
            sides++
        }
        if (plant == territory[pair.first - 1][pair.second] && plant == territory[pair.first - 1][pair.second - 1] && plant != territory[pair.first][pair.second - 1])
        {
            sides++
        }
        //right
        if (plant != territory[pair.first - 1][pair.second] && plant != territory[pair.first][pair.second + 1])
        {
            sides++
        }
        if (plant == territory[pair.first - 1][pair.second] && plant == territory[pair.first - 1][pair.second + 1] && plant != territory[pair.first][pair.second + 1])
        {
            sides++
        }

        //top
        if ((plant != territory[pair.first][pair.second - 1] && plant != territory[pair.first - 1][pair.second]))
            /*||  ((plant == territory[pair.first][pair.second - 1]) && (plant == territory[pair.first - 1][pair.second - 1])) && )*/
        {
            sides++
        }
        if (plant == territory[pair.first][pair.second - 1] && plant == territory[pair.first - 1][pair.second - 1] && plant != territory[pair.first - 1][pair.second])
        {
            sides++
        }
        //bottom
        if (plant != territory[pair.first][pair.second - 1] && plant != territory[pair.first + 1][pair.second])
        {
            sides++
        }
        if (plant == territory[pair.first][pair.second - 1] && plant == territory[pair.first + 1][pair.second - 1] && plant != territory[pair.first + 1][pair.second])
        {
            sides++
        }

    }
    //println(sides)
    return region.size * sides
}

fun calculateTotalSidesT2(allRegions : MutableList<MutableList<Pair<Int, Int>>>, territory : Array<Array<String>>) : Int
{

    var totalSides = 0
    for (region in allRegions)
    {
        totalSides += calculateSidesForOne(territory, region)
    }

    return totalSides
}
