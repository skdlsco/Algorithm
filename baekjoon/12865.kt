package `12865`

import java.lang.Math.max
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val cnt = scanner.nextInt()
    val maxWeight = scanner.nextInt()
    val dp = IntArray(maxWeight + 1) { 0 }

    repeat(cnt) {
        val weight = scanner.nextInt()
        val price = scanner.nextInt()

        (0..(maxWeight - weight)).reversed().forEach {
            dp[it + weight] = max(dp[it] + price, dp[it + weight])
        }
    }
    println(dp.maxOrNull())
}