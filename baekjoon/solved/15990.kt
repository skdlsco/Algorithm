package `15990`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

const val MOD = 1000000009

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    val dp = Array<Array<Int>>(100001) { Array(3) { 0 } }

    dp[1][0] = 1
    dp[2][1] = 1
    dp[3][2] = 1
    for (i in 1..100000) {
        dp[i][0] = ((dp[i - 1][1] + dp[i - 1][2]) % MOD + dp[i][0]) % MOD
        if (i > 1)
            dp[i][1] = ((dp[i - 2][0] + dp[i - 2][2]) % MOD + dp[i][1]) % MOD
        if (i > 2)
            dp[i][2] = ((dp[i - 3][0] + dp[i - 3][1]) % MOD + dp[i][2]) % MOD
    }

    repeat(T) {
        val n = reader.readLine().toInt()
        val sum = ((dp[n][0] + dp[n][1]) % MOD + dp[n][2]) % MOD
        writer.write("${sum}\n")
    }
    writer.flush()
}