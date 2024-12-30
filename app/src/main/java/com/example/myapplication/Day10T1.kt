package com.example.myapplication

import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\inputD10.txt"
    val lines = File(filePath).readLines()

    val rows = lines.size
    val cols = lines[0].length
    val twoDArray: Array<Array<Int?>> = Array(rows + 2) { Array<Int?>(cols + 2) { null } }

    for (i in 1..twoDArray.size - 2) {
        for (j in 1..twoDArray[i].size - 2) {
            if (lines[i - 1][j - 1] == '.')
            {
                twoDArray[i][j] = null
            }
            else
            {
                twoDArray[i][j] = lines[i  - 1][j - 1].toString().toInt()
            }
        }
    }

    val zeros = findZeros(twoDArray)
    //println(zeros)
    println(findAllTrailHeads(zeros, twoDArray))

}

fun findAllTrailHeads(zeros : MutableList<Pair<Int, Int>>, area : Array<Array<Int?>>): Int
{
    val all: MutableList<MutableList<MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>>> = mutableListOf()
    for (zero in zeros)
    {
        val newZero = Pair(zero, Pair(99, 99))
        all.add(findOneTrailHead(newZero, area))
    }
    val allInOneList = all.flatten().flatten().toMutableList()
    val uniqueCoordinates: Set<Pair<Pair<Int,Int>, Pair<Int,Int>>> = allInOneList.toSet()
    return uniqueCoordinates.size
}


fun findOneTrailHead(coordinates2: Pair<Pair<Int, Int>, Pair<Int, Int>>, area: Array<Array<Int?>>): MutableList<MutableList<Pair<Pair<Int,Int>, Pair<Int,Int>>>>
{
    var coordinates: Pair<Pair<Int, Int>, Pair<Int, Int>> = coordinates2
    if (coordinates.second.first == 99 && coordinates.second.second == 99)
    {
        val newX = coordinates.first.first
        val newY = coordinates.first.second
        coordinates = Pair(Pair(newX, newY), Pair(newX, newY))
    }
    val allCoordinates = mutableListOf<MutableList<Pair<Pair<Int, Int>, Pair<Int,Int>>>>()
    val number: Int = area[coordinates.second.first][coordinates.second.second] ?: return allCoordinates

    if (number == 9) {
        allCoordinates.add(mutableListOf(Pair(
            Pair(coordinates.first.first, coordinates.first.second), Pair(coordinates.second.first, coordinates.second.second))))
    }

    //top
    if (area[coordinates.second.first - 1][coordinates.second.second] != null &&
        number + 1 == area[coordinates.second.first - 1][coordinates.second.second])
    {
        val newCoordinates = Pair(coordinates.second.first - 1, coordinates.second.second)
        val oldCoordinates = Pair(coordinates.first.first, coordinates.first.second)
        allCoordinates.addAll(findOneTrailHead(Pair(oldCoordinates, newCoordinates), area))
    }
    //bottom
    if (area[coordinates.second.first + 1][coordinates.second.second] != null &&
        number + 1 == area[coordinates.second.first + 1][coordinates.second.second])
    {
        val newCoordinates = Pair(coordinates.second.first + 1, coordinates.second.second)
        val oldCoordinates = Pair(coordinates.first.first, coordinates.first.second)
        allCoordinates.addAll(findOneTrailHead(Pair(oldCoordinates, newCoordinates), area))
    }
    //left
    if (area[coordinates.second.first][coordinates.second.second - 1] != null &&
        number + 1 == area[coordinates.second.first][coordinates.second.second - 1])
    {
        val newCoordinates = Pair(coordinates.second.first, coordinates.second.second - 1)
        val oldCoordinates = Pair(coordinates.first.first, coordinates.first.second)
        allCoordinates.addAll(findOneTrailHead(Pair(oldCoordinates, newCoordinates), area))
    }
    //right
    if (area[coordinates.second.first][coordinates.second.second + 1] != null &&
        number + 1 == area[coordinates.second.first][coordinates.second.second + 1])
    {
        val newCoordinates = Pair(coordinates.second.first, coordinates.second.second + 1)
        val oldCoordinates = Pair(coordinates.first.first, coordinates.first.second)
        allCoordinates.addAll(findOneTrailHead(Pair(oldCoordinates, newCoordinates), area))
    }

    return allCoordinates
}


fun findZeros(area : Array<Array<Int?>>) : MutableList<Pair<Int, Int>>
{
    val coordinatesOfZeros = mutableListOf<Pair<Int, Int>>()
    for (i in 1..area.size - 2)
    {
        for (j in 1..area[i].size - 2)
        {

            if (area[i][j] == 0)
            {
                coordinatesOfZeros.add(Pair(i, j))
            }
        }
    }
    return coordinatesOfZeros
}

