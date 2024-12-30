package com.example.myapplication

import java.io.File

fun main()
{
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\Day15P1.txt"
    val lines = File(filePath).readLines()
    val area = convertTo2DArray(lines)

    val filePath2 = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\Day15P2.txt"
    val lines2 = File(filePath2).readLines()
    val directions = lines2.toString().split("")

    var x = 0
    var y = 0
    for (i in 0..area.size - 1)
    {
        for (j in 0..area[i].size - 1)
        {
            if (area[i][j].equals("@"))
            {
                x = i
                y = j
            }
        }
    }

    var info = Pair(area, Pair(x, y))
    var k = 0
    while (k < directions.size - 1)
    {
        info = makeOneStep(info, directions[k])
        k++
    }


    println(findGPS(info.first))

}

fun findGPS(area : Array<Array<String>>) : Long
{
    var sum : Long = 0
    for (i in 0..area.size - 1)
    {
        for (j in 0..area[i].size - 1)
        {
            if (area[i][j].equals("O"))
            {
                sum += 100 * i + j
            }
        }
    }
    return sum
}
fun makeOneStep(info : Pair<Array<Array<String>>, Pair<Int, Int>>, direction : String): Pair<Array<Array<String>>, Pair<Int, Int>>
{
    val area = info.first
    val x = info.second.first
    val y = info.second.second
    var newX = x
    var newY = y

    if (direction.equals("^"))
    {
        var Ox = -1
        var go : Boolean = true
        var i = x - 1
        while (go)
        {
            if (area[i][y].equals("O"))
            {
                i--
            }
            else if (area[i][y].equals("#"))
            {
                go = false
            }
            else if (area[i][y].equals("."))
            {
                Ox = i
                go = false
            }
        }
        if (Ox == x - 1 )
        {
            area[x][y] = "."
            area[x - 1][y] = "@"
            newX -= 1
        }
        else if (Ox >= 0)
        {
            area[x][y] = "."
            area[x - 1][y] = "@"
            newX -= 1
            area[Ox][y] = "O"
        }
    }

    else if (direction.equals("v"))
    {
        var Ox = -1
        var go : Boolean = true
        var i = x + 1
        while (go)
        {
            if (area[i][y].equals("O"))
            {
                i++
            }
            else if (area[i][y].equals("#"))
            {
                go = false
            }
            else if (area[i][y].equals("."))
            {
                Ox = i
                go = false
            }
        }
        if (Ox == x + 1)
        {
            area[x][y] = "."
            area[x + 1][y] = "@"
            newX += 1
        }
        else if (Ox >= 0)
        {
            area[x][y] = "."
            area[x + 1][y] = "@"
            newX += 1
            area[Ox][y] = "O"
        }
    }

    else if (direction.equals(">"))
    {
        var Oy = -1
        var go : Boolean = true
        var j = y + 1
        while (go)
        {
            if (area[x][j].equals("O"))
            {
                j++
            }
            else if (area[x][j].equals("#"))
            {
                go = false
            }
            else if (area[x][j].equals("."))
            {
                Oy = j
                go = false
            }
        }
        if (Oy == y + 1)
        {
            area[x][y] = "."
            area[x][y + 1] = "@"
            newY += 1
        }
        else if (Oy >= 0)
        {
            area[x][y] = "."
            area[x][y + 1] = "@"
            newY += 1
            area[x][Oy] = "O"
        }
    }

    else if (direction.equals("<"))
    {
        var Oy = -1
        var go : Boolean = true
        var j = y - 1
        while (go)
        {
            if (area[x][j].equals("O"))
            {
                j--
            }
            else if (area[x][j].equals("#"))
            {
                go = false
            }
            else if (area[x][j].equals("."))
            {
                Oy = j
                go = false
            }
        }
        if (Oy == y - 1)
        {
            area[x][y] = "."
            area[x][y - 1] = "@"
            newY -= 1
        }
        else if (Oy >= 0)
        {
            area[x][y] = "."
            area[x][y - 1] = "@"
            newY -= 1
            area[x][Oy] = "O"
        }
    }
    return Pair(area, Pair(newX, newY))
}

fun convertTo2DArray(lines: List<String>): Array<Array<String>> {
    val rows = lines.size
    val cols = lines[0].length
    val area = Array(rows) { Array(cols) { "" } }

    for (i in lines.indices)
    {
        for (j in lines[i].indices)
        {
            area[i][j] = lines[i][j].toString()
        }
    }
    return area
}
