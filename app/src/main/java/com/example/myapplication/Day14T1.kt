package com.example.myapplication

import java.io.File

fun main() {
    val filePath =
        "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\inputD14.txt"
    val lines = File(filePath).readLines()

    val xAxis = 101
    val yAxis = 103
    var info = parseInput(lines)


    var i = 0
    while(i < 8160)
    {
        info = inOneSecond(info,xAxis,yAxis)
        i++
    }

    val area = createAreaAndLocateRobots(info, xAxis, yAxis)


    for (i in area)
    {
        for (j in i)
        {
            print(j)
        }
        println()
    }

    println(quadrants(area, xAxis, yAxis))
}

fun quadrants(locations : MutableList<MutableList<String>>, xAxis : Int, yAxis : Int) : Int
{
    var sum  = 0
    var x1toDelete : Int = 0
    var x2toDelete = 0
    var y1toDelete = 0
    var y2toDelete = 0

    if (xAxis % 2 == 1) { x1toDelete = xAxis / 2
    x2toDelete = x1toDelete}
    else { x2toDelete = xAxis / 2
        x1toDelete = xAxis / 2 - 1 }
    if (yAxis % 2 == 1) { y1toDelete = yAxis / 2
    y2toDelete = y1toDelete}
    else
    { y2toDelete = yAxis / 2
        y1toDelete = yAxis / 2 - 1 }


    var firstQuoter = 0
    var secondQuoter = 0
    var thirdQuoter = 0
    var fourthQuoter = 0

    //1st
    for (i in 0..y1toDelete - 1)
    {
        for (j in 0..x1toDelete - 1)
        {
            //print(locations[i][j])
            if (!locations[i][j].equals("."))
            {
                firstQuoter += locations[i][j].toInt()

            }
        }
        //println()
        for (j in x2toDelete + 1.. locations[i].size - 1)
        {
            //print(locations[i][j])
            if (!locations[i][j].equals("."))
            {
                secondQuoter += locations[i][j].toInt()
                //println("$i $j")
            }
        }
        //println()
    }

    //2nd

    for (i in y2toDelete + 1..locations.size - 1)
    {
        for (j in 0..x1toDelete - 1)
        {
            //print(locations[i][j])
            if (!locations[i][j].equals("."))
            {
                thirdQuoter += locations[i][j].toInt()
                //println("$i $j")
            }
        }
        //println()
        for (j in x2toDelete + 1.. locations[i].size - 1)
        {
            //print(locations[i][j])
            if (!locations[i][j].equals("."))
            {
                fourthQuoter += locations[i][j].toInt()
                //println("$i $j")
            }
        }
        //println()
    }

    println("$firstQuoter $secondQuoter $thirdQuoter $fourthQuoter")
    return firstQuoter * secondQuoter * thirdQuoter * fourthQuoter



}
fun inOneSecond(info : MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>,xAxis : Int, yAxis : Int) :
        MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>
{
    val updatedInfo = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
    for (robot in info)
    {
        var x = (robot.first.first + robot.second.first)
        var y = (robot.first.second + robot.second.second)

        if (x < 0) { x += xAxis }
        else { x = x % xAxis }
        if (y < 0) { y += yAxis }
        else {y = y % yAxis }

        updatedInfo.add(Pair(Pair(x,y), Pair(robot.second.first, robot.second.second)))
    }

    return updatedInfo
}

fun createAreaAndLocateRobots(info : MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>,xAxis : Int, yAxis : Int): MutableList<MutableList<String>>
{

    val area = MutableList(yAxis) { MutableList(xAxis) { "." } }

    for (robot in info)
    {
        val x = robot.first.first
        val y = robot.first.second
        if (area[y][x].equals("."))
        {
            area[y][x] = "1"
        }
        else
        {
            area[y][x] = (area[y][x].toInt() + 1).toString()
        }
    }
    return area
}

fun parseInput(lines: List<String>): MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>
{
    val info = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
    for (line in lines)
    {
        val regex = Regex("""p=(-?\d+),(-?\d+) v=(-?\d+),(-?\d+)""")
        val matchResult = regex.find(line)

        if (matchResult != null) {
            val (px, py, vx, vy) = matchResult.destructured
            info.add(Pair(Pair(px.toInt(), py.toInt()), Pair(vx.toInt(), vy.toInt())))
        }
    }
    return info
}