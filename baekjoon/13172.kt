package `13172`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val MOD = 1000000007L
fun pow(n: Long, b: Long): Long {
    if (b == 1L)
        return n
    var temp = pow(n, b / 2) % MOD
    temp = (temp * temp) % MOD
    if (b % 2 == 1L)
        temp = (temp * n) % MOD
    return temp
}

fun getExpected(N: Long, S: Long): Long {
    val inverted = pow(N, MOD - 2)
    return S * inverted % MOD
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val M = reader.readLine().toInt()
    var result = 0L
    repeat(M) {
        val (N, S) = reader.readLine().split(" ").map { it.toLong() }
        val expected = getExpected(N, S)
        result = (result + expected) % MOD
    }
    writer.write("${result}\n")
    writer.flush()
}