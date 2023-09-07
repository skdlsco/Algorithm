package `2560`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val MOD = 1000

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (a, b, d, N) = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Array<Int>>(N + 1) { Array(4) { 0 } }
    // 태어난 수
    dp[0][0] = 1
    // 응애 수
    dp[0][1] = 1
    // 성체 수
    dp[0][2] = 0
    // 늙은이 수
    dp[0][3] = 0
    for (i in 1..N) {
        dp[i][1] = dp[i - 1][1]
        dp[i][2] = dp[i - 1][2]
        dp[i][3] = dp[i - 1][3]
        if (i >= d) {
            dp[i][3] -= dp[i - d][0]
        }
        if (i >= b) {
            dp[i][2] -= dp[i - b][0]
            dp[i][3] += dp[i - b][0]
        }
        if (i >= a) {
            dp[i][1] -= dp[i - a][0]
            dp[i][2] += dp[i - a][0]
        }
        dp[i][1] = (dp[i][1] + 1000) % MOD
        dp[i][2] = (dp[i][2] + 1000) % MOD
        dp[i][3] = (dp[i][3] + 1000) % MOD
        dp[i][0] = dp[i][2]
        dp[i][1] += dp[i][0]
    }

    val ans = (dp[N][1] + dp[N][2] + dp[N][3]) % MOD
    writer.write("${ans}\n")
    writer.flush()
}
