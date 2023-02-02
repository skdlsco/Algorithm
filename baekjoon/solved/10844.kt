package `10844`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val MOD = 1000000000
    val dp = Array<Array<Int>>(N + 1) { Array(10) { 0 } }
    for (i in 1..9) {
        dp[1][i] = 1
    }
    for (i in 2..N) {
        for (j in 0..9) {
            if (j > 0)
                dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD
            if (j < 9)
                dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD
        }
    }
    var sum = 0
    dp[N].forEach {
        sum = (sum + it) % MOD
    }
    println(sum)
}
