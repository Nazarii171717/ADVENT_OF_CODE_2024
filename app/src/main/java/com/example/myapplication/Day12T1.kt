package com.example.myapplication
import java.io.File
fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD12.txt"
    val lines = File(filePath).readLines()

    val rows = lines.size
    val cols = lines[0].length
    val territory = Array(rows) { Array(cols) { "" } }

    for (i in 0..lines.size - 1) {
        for (j in 0..lines[i].length - 1) {
            territory[i][j] = lines[i][j].toString()
        }
    }
    val allRegions = findAllRegions(territory)
    println(calculateTotalPrice(allRegions, territory))

}

fun calculateTotalPrice(allRegions : MutableList<MutableList<Pair<Int, Int>>>, territory : Array<Array<String>>) : Int
{
    var totalSum = 0
    for (region in allRegions)
    {
        totalSum += calculatePriceForOne(territory, region)
    }
    return totalSum
}

fun calculatePriceForOne(territory : Array<Array<String>>, region: MutableList<Pair<Int, Int>>) : Int
{
    var perimeter = 0
    for (pair in region)
    {
        if (pair.first == territory.size - 1 || (pair.first != territory.size - 1 && !territory[pair.first][pair.second].equals(territory[pair.first + 1][pair.second])))
        {
            perimeter++
        }
        if (pair.first == 0 || (pair.first != 0 && !territory[pair.first][pair.second].equals(territory[pair.first - 1][pair.second])))
        {
            perimeter++
        }
        if (pair.second == territory[0].size - 1 || (pair.second != territory[0].size - 1 && !territory[pair.first][pair.second].equals(territory[pair.first][pair.second + 1])))
        {
            perimeter++
        }
        if (pair.second == 0 || (pair.second != 0 && !territory[pair.first][pair.second].equals(territory[pair.first][pair.second - 1])))
        {
            perimeter++
        }
    }
    return region.size * perimeter
}

fun findAllRegions(territory : Array<Array<String>>) : MutableList<MutableList<Pair<Int, Int>>>
{
    val regions = mutableListOf<MutableList<Pair<Int, Int>>>()

    for (i in 0..territory.size - 1)
    {
        for (j in 0.. territory[0].size - 1) {
            if (territory[i][j] != ".") {
                val newRegion = findOneRegion(i, j, territory)
                if (!doesExist(regions, newRegion)) {
                    regions.add(newRegion)
                }
            }
        }
    }
    return regions
}

fun doesExist(regions: MutableList<MutableList<Pair<Int, Int>>>,
    newRegion: MutableList<Pair<Int, Int>>): Boolean
{
    val newRegionSorted = newRegion.toSortedSet(compareBy({ it.first }, { it.second }))
    for (region in regions) {
        if (region.toSortedSet(compareBy({ it.first }, { it.second })) == newRegionSorted) {
            return true
        }
    }
    return false
}

fun findOneRegion(x1: Int, y1: Int, territory : Array<Array<String>>) : MutableList<Pair<Int, Int>>
{
    var x = x1
    var y = y1
    var counter = 0
    val region = mutableListOf<Pair<Int, Int>>()
    region.add(Pair(x,y))

    while (true)
    {
        var counter1 = 0
        while (y != territory[0].size - 1 && territory[x][y + 1].equals(territory[x][y]))
        {
            y += 1
            counter1++
            if (!region.contains(Pair(x, y)))
            {
                region.add(Pair(x, y))
            }
            else
            {
                break
            }
        }
        y-= counter1


        var counter2 = 0
        while (y != 0 && territory[x][y - 1].equals(territory[x][y]))
        {
            y -= 1
            counter2--
            if (!region.contains(Pair(x, y)))
            {
                region.add(Pair(x, y))
            }
            else
            {
                break
            }
        }
        y-=counter2


        var counter3 = 0
        while (x != territory.size - 1 && territory[x + 1][y].equals(territory[x][y]))
        {
            x += 1
            counter3++
            if (!region.contains(Pair(x, y)))
            {
                region.add(Pair(x, y))
            }
            else
            {
                break
            }
        }
        x-=counter3


        var counter4 = 0
        while (x != 0 && territory[x - 1][y].equals(territory[x][y]))
        {
            x -= 1
            counter4--
            if (!region.contains(Pair(x, y)))
            {
                region.add(Pair(x, y))
            }
            else
            {
                break
            }
        }
        x-= counter4


        if (counter == region.size - 1)
        {
            break
        }
        counter++
        x = region[counter].first
        y = region[counter].second

    }
    return region
}