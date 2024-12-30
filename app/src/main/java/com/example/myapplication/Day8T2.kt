import java.io.File

fun main() {
    val filePath =
        "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\inputD8"
    val lines = File(filePath).readLines()
    val city = convertTo2DArray(lines)

    val antennas = allAntennas(city)

    val uniqueAntinodesCount = findAllAntinodesT2(antennas, city)
    println(uniqueAntinodesCount)
}

/*fun returnAllPossibleAntinodes(ant1 : Pair<Int, Int>, ant2 : Pair<Int, Int>, area: Array<Array<String>>)
: MutableList<Pair<Int, Int>>
{
    val (x1, y1) = ant1
    val (x2, y2) = ant2

}*/

fun findAllAntinodesT2(locations2: MutableMap<String, MutableList<Pair<Int, Int>>>, area: Array<Array<String>>
): Int {
    val rows = area.size
    val cols = area[0].size
    val antinodes = mutableSetOf<Pair<Int, Int>>()

    for ((_, locations) in locations2) {
        for (i in locations.indices) {
            for (j in i + 1 until locations.size)
            {
                val (x1, y1) = locations[i]
                val (x2, y2) = locations[j]

                var antX1 = x1 - (x2 - x1)
                var antY1 = y1 - (y2 - y1)
                var antX2 = x2 + (x2 - x1)
                var antY2 = y2 + (y2 - y1)

                while (isValid(antX1, antY1, rows, cols) || isValid(antX2, antY2, rows, cols))
                {
                    if (isValid(antX1, antY1, rows, cols)) {
                        antinodes.add(Pair(antX1, antY1))
                    }
                    if (isValid(antX2, antY2, rows, cols)) {
                        antinodes.add(Pair(antX2, antY2))
                    }
                    antX1 -= (x2 - x1)
                    antY1 -= (y2 - y1)
                    antX2 += (x2 - x1)
                    antY2 += (y2 - y1)
                }
            }
        }
        antinodes.addAll(locations)
    }
    return antinodes.size
}
