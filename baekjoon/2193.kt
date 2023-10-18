package `2193`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    // N자리 수에서의 이친수.
    val dp = Array<Long>(91) { 0 }
    dp[0] = 0
    dp[1] = 1
    for (i in 2..90) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }
    println(dp[N])
}