package `11057`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val MOD = 10007

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val dp = Array<Array<Int>>(N + 1) { Array(10) { 0 } }
    for (i in 0..9) {
        dp[1][i] = 1
    }
    for (i in 2..N) {
        var sum = 0
        for (j in 0..9) {
            sum += dp[i - 1][j]
            dp[i][j] = sum % MOD
        }
    }
    writer.write("${dp[N].sumOf { it } % MOD}\n")
    writer.flush()
}