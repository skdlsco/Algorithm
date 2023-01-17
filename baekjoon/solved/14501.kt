package clear

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val dp = Array<Int>(N + 1) { 0 }

    repeat(N) {
        val (len, value) = reader.readLine().split(" ").map { it.toInt() }
        if (it + len <= N) {
            dp[it + len] = max(dp[it + len], dp[it] + value)
        }
        if (it < N)
            dp[it + 1] = max(dp[it + 1], dp[it])
    }
    println(dp.maxOrNull())
}