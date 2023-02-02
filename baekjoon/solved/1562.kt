package `1562`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val dp = Array<Array<Array<Int>>>(N + 1) { Array(1 shl 10) { Array(10) { 0 } } }
    val MOD = 1000000000
    for (i in 1..9) {
        dp[1][1 shl i][i] = 1
    }

    for (i in 1 until N) {
        for (j in 0 until (1 shl 10)) {
            for (k in 0..9) {
                if (k > 0)
                    dp[i + 1][j or (1 shl k)][k] = (dp[i + 1][j or (1 shl k)][k] + dp[i][j][k - 1]) % MOD
                if (k < 9)
                    dp[i + 1][j or (1 shl k)][k] = (dp[i + 1][j or (1 shl k)][k] + dp[i][j][k + 1]) % MOD
            }
        }
    }

    var sum = 0
    dp[N][1023].forEach {
        sum = (sum + it) % MOD
    }
    println(sum)
}