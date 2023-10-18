package `1248`

import java.io.BufferedReader
import java.io.InputStreamReader

val negative = (-10..-1).toList().toIntArray()
val positive = (1..10).toList().toIntArray()
val zero = (0..0).toList().toIntArray()

fun f(n: Int, matrix: Array<Array<Char>>, check: Array<Int>, depth: Int, sum: Int) : Boolean {
    if (depth == n) {
        println(check.joinToString(" "))
        return true
    }
    val nums: IntArray = when (matrix[depth][depth]) {
        '-' -> negative
        '+' -> positive
        else -> zero
    }
    nums.forEach {
        var tmp = sum + it
        val isPass = (0..depth).all { y ->
            val result = when (matrix[y][depth]) {
                '-' -> tmp < 0
                '+' -> tmp > 0
                else -> tmp == 0
            }
            tmp -= check[y]
            result
        }
        if (isPass) {
            check[depth] = it
            if (f(n, matrix, check, depth + 1, sum + it))
                return true
        }
    }
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val n = reader.readLine().toInt()
    val matrix = Array<Array<Char>>(n) { Array(n) { ' ' } }
    val input = reader.readLine().toCharArray()
    val iter = input.iterator()
    for (y in 0 until n) {
        for (x in y until n) {
            matrix[y][x] = iter.nextChar()
        }
    }
    val check = Array<Int>(n) { 0 }
    f(n, matrix, check, 0, 0)
}