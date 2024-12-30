package com.example.myapplication

import java.io.File

fun main() {
    val filePath = "C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\main\\java\\com\\example\\myapplication\\inputD7.txt"
    val lines = File(filePath).readLines()

    val equations = lines.map { line ->
        val parts = line.split(": ")
        val target = parts[0].toLong()
        val numbers = parts[1].split(" ").map { it.toLong() }
        target to numbers
    }

    var totalCalibrationResult: Long = 0

    for ((target, numbers) in equations) {
        if (canProduceTargetT2(numbers, target)) {
            totalCalibrationResult += target
        }
    }

    println(totalCalibrationResult)
}

fun canProduceTargetT2(numbers: List<Long>, target: Long): Boolean {
    val operators = listOf("+", "*", "||")

    fun dfs(index: Int, currentResult: Long): Boolean {
        if (index == numbers.size) {
            return currentResult == target
        }

        for (operator in operators) {
            val nextResult = when (operator) {
                "+" -> currentResult + numbers[index]
                "*" -> currentResult * numbers[index]
                "||" -> "$currentResult${numbers[index]}".toLong()
                else -> throw IllegalArgumentException("")
            }
            println(nextResult)
            println()
            if (dfs(index + 1, nextResult)) {
                return true
            }
        }

        return false
    }

    return dfs(1, numbers[0])
}
