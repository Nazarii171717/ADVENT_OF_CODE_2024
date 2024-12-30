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

    var result: Long = 0

    for ((target, numbers) in equations) {
        if (canProduceTarget(numbers, target)) {
            result += target
        }
    }

    println(result)
}

fun canProduceTarget(numbers: List<Long>, target: Long): Boolean {
    val operators = listOf("+", "*")

    fun dfs(index: Int, currentResult: Long): Boolean {
        if (index == numbers.size) {
            return currentResult == target
        }

        for (operator in operators) {
            val nextResult = when (operator) {
                "+" -> currentResult + numbers[index]
                "*" -> currentResult * numbers[index]
                else -> throw IllegalArgumentException("")
            }
            println(nextResult)


            if (dfs(index + 1, nextResult)) {
                return true
            }
        }

        return false
    }
    return dfs(1, numbers[0])
}
