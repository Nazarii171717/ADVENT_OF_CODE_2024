import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD3.txt"
    val input = File(filePath).readLines().toString()


    val mulRegex = Regex("mul\\((\\d+),(\\d+)\\)")
    val instructionRegex = Regex("(mul\\((\\d+),(\\d+)\\)|do\\(\\)|don\\'t\\(\\))")

    var isEnabled = true
    var part2Sum = 0

    instructionRegex.findAll(input).forEach { matchResult ->
        val instruction = matchResult.value

        when {
            instruction.startsWith("mul(") -> {
                if (isEnabled) {
                    val (x, y) = mulRegex.matchEntire(instruction)!!.destructured
                    part2Sum += x.toInt() * y.toInt()
                }
            }
            instruction == "do()" -> isEnabled = true
            instruction == "don't()" -> isEnabled = false
        }
    }

    println("Part 2 Sum: $part2Sum")
}
