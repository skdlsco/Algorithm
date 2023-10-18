package `11048`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N + 1) { Array(M + 1) { 0 } }
    val dp = Array<Array<Int>>(N + 1) { Array(M + 1) { 0 } }
    for (y in 1..N) {
        val line = reader.readLine().split(" ").map { it.toInt() }
        for (x in 1..M) {
            map[y][x] = line[x - 1]
        }
    }
    for (y in 1..N) {
        for (x in 1..M) {
            dp[y][x] = max(dp[y - 1][x], dp[y - 1][x - 1])
            dp[y][x] = max(dp[y][x], dp[y][x - 1])
            dp[y][x] += map[y][x]
        }
    }
    writer.write("${dp[N][M]}\n")
    writer.flush()
}