import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\inputD6.txt"
    val grid = File(filePath).readLines().map { it.toCharArray() }
    val guard = findGuard(grid)
    tracePath(grid, guard.first, guard.second)
    println(countObstructions(grid, guard.first, guard.second))
}

val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

fun findGuard(grid: List<CharArray>): Pair<Int, Int>
{
    for (r in grid.indices)
    {
        for (c in grid[r].indices)
        {
            if (grid[r][c] == '^') return r to c
        }
    }
    return -1 to -1
}

fun tracePath(grid: List<CharArray>, row: Int, col: Int)
{
    var r = row
    var c = col
    var d = 0

    while (true) {
        grid[r][c] = 'X'
        val (dr, dc) = directions[d]
        val nr = r + dr
        val nc = c + dc

        if (nr !in grid.indices || nc !in grid[0].indices) break
        if (grid[nr][nc] == '#') d = (d + 1) % 4
        else { r = nr; c = nc }
    }
}

fun countObstructions(grid: List<CharArray>, guardRow: Int, guardCol: Int): Int
{
    var obstructions = 0

    for (r in grid.indices)
    {
        for (c in grid[r].indices)
        {
            if (grid[r][c] != 'X' || (r == guardRow && c == guardCol)) continue
            grid[r][c] = '#'
            if (detectLoop(grid, guardRow, guardCol)) obstructions++
            grid[r][c] = if (grid[r][c] == '#') 'O' else '.'
        }
    }
    return obstructions
}

fun detectLoop(grid: List<CharArray>, row: Int, col: Int): Boolean
{
    var r = row
    var c = col
    var d = 0
    val visited = mutableSetOf<String>()
    while (true) {
        if (!visited.add("$r-$c-$d")) return true
        val (dr, dc) = directions[d]
        val nr = r + dr
        val nc = c + dc

        if (nr !in grid.indices || nc !in grid[0].indices) return false
        if (grid[nr][nc] == '#') d = (d + 1) % 4
        else { r = nr; c = nc }
    }
}