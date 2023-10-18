package `2133`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()

    val dp = IntArray(31) { 0 }
    dp[1] = 2
    dp[2] = 3
    for (i in 3..30) {
        if (i % 2 == 1) {
            dp[i] = dp[i - 1] * 2 + dp[i - 2]
        } else {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
    }
    if (N % 2 == 0)
        println(dp[N])
    else
        println(0)
}