package `1309`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val MOD = 9901

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val dp = Array<Array<Int>>(N + 1) { Array(2) { 0 } }
    dp[0][0] = 1
    for (i in 1..N) {
        dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD
        dp[i][1] = (((dp[i - 1][0] * 2) % MOD) + dp[i - 1][1]) % MOD
    }
    writer.write("${(dp[N][0] + dp[N][1]) % MOD}\n")
    writer.flush()
}