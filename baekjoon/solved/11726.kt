package `11726`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val dp = Array<Int>(N + 2) { 0 }
    dp[0] = 0
    dp[1] = 1
    dp[2] = 2
    (3..N).forEach {
        dp[it] = (dp[it - 1] + dp[it - 2]) % 10007
    }
    println(dp[N])
}