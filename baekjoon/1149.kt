package `1149`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = Array<Array<Int>>(N) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }
    val dp = Array<Array<Int>>(N) { Array(3) { 0 } }
    dp[0][0] = arr[0][0]
    dp[0][1] = arr[0][1]
    dp[0][2] = arr[0][2]
    (1 until N).forEach {
        dp[it][0] = min(dp[it - 1][1], dp[it - 1][2]) + arr[it][0]
        dp[it][1] = min(dp[it - 1][0], dp[it - 1][2]) + arr[it][1]
        dp[it][2] = min(dp[it - 1][0], dp[it - 1][1]) + arr[it][2]
    }
    println(min(min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]))
}