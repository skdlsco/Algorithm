package `1629`

import java.io.BufferedReader
import java.io.InputStreamReader

fun `1629`(A: Long, B: Long, C: Long): Long {
    if (B == 0L) return 1
    val result = `1629`(A, B / 2, C)

    return ((result * result) % C) * (if (B % 2L == 1L) A else 1) % C
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (A, B, C) = reader.readLine().split(" ").map { it.toLong() }

    println(`1629`(A, B, C))
}