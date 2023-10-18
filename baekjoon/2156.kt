package `2156`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val dp = Array<Array<Int>>(N) { Array(2) { 0 } }
    val nums = Array<Int>(N) { 0 }
    repeat(N) {
        nums[it] = reader.readLine().toInt()
    }
    dp[0][0] = nums[0]
    if (N > 1) {
        dp[1][0] = nums[0] + nums[1]
        dp[1][1] = nums[0]
    }
    for (i in 2 until N) {
        // 마신다.
        dp[i][0] = max(dp[i - 1][1] + nums[i], dp[i - 2][1] + nums[i] + nums[i - 1])
        // 안마신다.
        dp[i][1] = max(dp[i - 1][0], dp[i - 1][1])
    }
    println(max(dp[N - 1][0], dp[N - 1][1]))
}