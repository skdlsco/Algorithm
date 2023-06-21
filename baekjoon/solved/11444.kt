package `11444`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// f(n) = f(n - 1) + f(n - 2)
val MOD = 1000000007

fun multiply(m1: Array<Array<Long>>, m2: Array<Array<Long>>): Array<Array<Long>> {
    val r = Array<Array<Long>>(2) { Array(2) { 0 } }
    for (i in 0 until 2) {
        for (j in 0 until 2) {
            for (k in 0 until 2) {
                r[i][j] = (r[i][j] + m1[i][k] * m2[k][j]) % MOD
            }
        }
    }
    return r
}

fun pow(m: Array<Array<Long>>, b: Long): Array<Array<Long>> {
    if (b <= 1)
        return m
    if (b == 2L)
        return multiply(m, m)
    var temp = pow(m, b / 2)
    temp = multiply(temp, temp)
    if (b % 2 == 1L)
        temp = multiply(temp, m)
    return temp
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toLong()
    val m = arrayOf(arrayOf(1L, 1L), arrayOf(1L, 0L))
    val result = when (N) {
        1L -> {
            1
        }

        0L -> {
            0
        }

        else -> {
            val matrix = pow(m, N )
            matrix[1][0]
        }
    }
    writer.write("${result}")
    writer.flush()
}