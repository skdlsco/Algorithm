package `15988`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val MOD = 1000000009

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    val dp = Array<Int>(1000001) { 0 }
    dp[0] = 1
    dp[1] = 1
    dp[2] = 2
    for (i in 3..1000000) {
        dp[i] = (((dp[i - 1] + dp[i - 2]) % MOD) + dp[i - 3]) % MOD
    }
    repeat(T) {
        val N = reader.readLine().toInt()
        writer.write("${dp[N]}\n")
    }
    writer.flush()
}