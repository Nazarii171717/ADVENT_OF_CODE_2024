package com.example.myapplication

import java.io.File



fun main() {

    val filePath =
        "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\inputD6.txt"
    val lines = File(filePath).readLines()

    val rows = lines.size
    val cols = lines[0].length
    val area = Array(rows) { Array(cols) { "" } }

    var x = 0
    var y = 0

    for (i in 0..lines.size - 1) {
        for (j in 0..lines[i].length - 1)
        {
            area[i][j] = lines[i][j].toString()
            if (area[i][j].equals("^"))
            {
                x = i
                y = j

            }
        }
    }
    var count = 0
    fun check(x: Int, y: Int): Boolean
    {
        return x != 0 && x != area.size - 1 && y != 0 && y != area[0].size - 1
    }




    while (check(x,y))
    {
        if (area[x][y].equals("^"))
        {
            while (check(x ,y))
            {
                x -= 1
                if (area[x][y].equals("#"))
                {
                    area[x + 1][y] = ">"
                    x += 1
                    break
                }
                else
                {
                    if (area[x][y].equals("."))
                    {
                        area[x][y] = "X"
                        count += 1
                    }
                }
            }
        }
        else if (area[x][y].equals(">"))
        {
            while (check(x,y ))
            {
                y += 1
                if (area[x][y].equals("#"))
                {
                    area[x][y - 1] = "v"
                    y -= 1
                    break
                }
                else
                {
                    if (area[x][y].equals("."))
                    {
                        area[x][y] = "X"
                        count += 1
                    }
                }
            }
        }

        else if (area[x][y].equals("v"))
        {
            while (check(x ,y))
            {
                x += 1
                if (area[x][y].equals("#"))
                {
                    area[x - 1][y] = "<"
                    x -= 1
                    break
                }
                else
                {
                    if (area[x][y].equals("."))
                    {
                        area[x][y] = "X"
                        count += 1
                    }
                }
            }
        }

        else if (area[x][y].equals("<"))
        {
            while (check(x,y))
            {
                y -= 1
                if (area[x][y].equals("#"))
                {
                    area[x][y + 1] = "^"
                    y += 1
                    break
                }
                else
                {
                    if (area[x][y].equals("."))
                    {
                        area[x][y] = "X"
                        count += 1
                    }
                }
            }
        }
        else {
            break
        }
    }

    println(count + 1)

}