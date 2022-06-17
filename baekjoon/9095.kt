package clear

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val T = reader.readLine().toInt()
    val dp = Array<Int>(12) { 0 }
    dp[0] = 0
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4
    (4..11).forEach {
        dp[it] = dp[it - 1] + dp[it - 2] + dp[it - 3]
    }
    repeat(T) {
        val N = reader.readLine().toInt()
        println(dp[N])
    }
}