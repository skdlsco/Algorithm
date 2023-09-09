package `2698`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    // dp[i][j][k] = i개 비트 중 j개의 인접 비트 마지막이 k
    val dp = Array<Array<Array<Int>>>(101) { Array(101) { Array(2) { 0 } } }
    dp[1][0][0] = 1
    dp[1][0][1] = 1
    for (i in 2..100) {
        for (j in 0 until i) {
            dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1]
            dp[i][j][1] = dp[i - 1][j][0] + if (j >= 1) dp[i - 1][j - 1][1] else 0
        }
    }
    repeat(T) {
        val (n, k) = reader.readLine().split(" ").map { it.toInt() }
        writer.write("${dp[n][k][1] + dp[n][k][0]}\n")
    }
    writer.flush()
}
    