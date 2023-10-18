package `2579`

import java.util.*
import kotlin.math.max

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val arr = Array<Int>(N + 3) { 0 }
    val dp = Array<Int>(N + 3) { 0 }
    repeat(N) {
        arr[it + 1] = scanner.nextInt()
    }
    dp[0] = arr[0]
    dp[1] = arr[1]
    (0 until N).forEach {
        dp[it + 2] = max(dp[it + 2], dp[it] + arr[it + 2])
        dp[it + 3] = max(dp[it + 3], dp[it] + arr[it + 1] + arr[it + 3])
    }
    dp[N] = max(dp[N], dp[N - 1] + arr[N])
    println(dp[N])
}