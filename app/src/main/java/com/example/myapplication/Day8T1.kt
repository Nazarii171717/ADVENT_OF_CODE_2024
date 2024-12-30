import java.io.File

fun main() {
    val filePath =
        "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\inputD8"
    val lines = File(filePath).readLines()
    val city = convertTo2DArray(lines)

    val antennas = allAntennas(city)

    val uniqueAntinodesCount = findAllAntinodes(antennas, city)
    println(uniqueAntinodesCount)
}

fun findAllAntinodes(locations2: MutableMap<String, MutableList<Pair<Int, Int>>>,
                     area: Array<Array<String>>): Int
{
    val rows = area.size
    val cols = area[0].size
    val uniqueAntinodes = mutableSetOf<Pair<Int, Int>>()

    for ((letter, locations) in locations2)
    {
        for (i in locations.indices)
        {
            for (j in i + 1 until locations.size)
            {
                val (x1, y1) = locations[i]
                val (x2, y2) = locations[j]

                val antX1 = x1 - (x2 - x1)
                val antY1 = y1 - (y2 - y1)
                val antX2 = x2 + (x2 - x1)
                val antY2 = y2 + (y2 - y1)

                if (isValid(antX1, antY1, rows, cols))
                {
                    uniqueAntinodes.add(Pair(antX1, antY1))
                }
                if (isValid(antX2, antY2, rows, cols))
                {
                    uniqueAntinodes.add(Pair(antX2, antY2))
                }
            }
        }
    }

    return uniqueAntinodes.size
}

fun isValid(x: Int, y: Int, rows: Int, cols: Int): Boolean
{
    return x in 0 until rows && y in 0 until cols
}

fun allAntennas(area: Array<Array<String>>): MutableMap<String, MutableList<Pair<Int, Int>>>
{
    val uniqueCoordinates = mutableMapOf<String, MutableList<Pair<Int, Int>>>()
    for (i in area.indices) {
        for (j in area[i].indices) {
            val letter = area[i][j]
            if (letter != "." && letter != "#") {
                uniqueCoordinates.computeIfAbsent(letter) { mutableListOf() }
                uniqueCoordinates[letter]!!.add(Pair(i, j))
            }
        }
    }
    return uniqueCoordinates
}

fun convertTo2DArray(lines: List<String>): Array<Array<String>> {
    val rows = lines.size
    val cols = lines[0].length
    val area = Array(rows) { Array(cols) { "." } }

    for (i in lines.indices)
    {
        for (j in lines[i].indices)
        {
            area[i][j] = lines[i][j].toString()
        }
    }
    return area
}
