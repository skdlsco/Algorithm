package `7579`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val memoryArr = reader.readLine().split(" ").map { it.toInt() }
    val costArr = reader.readLine().split(" ").map { it.toInt() }

    // dp[cost] = memory
    val dp = Array<Int>(10001) { 0 }
    for (appIdx in 0 until N) {
        val cost = costArr[appIdx]
        val memory = memoryArr[appIdx]
        for (i in 10000 downTo cost) {
            dp[i] = max(dp[i], dp[i - cost] + memory)
        }
    }
    val result = dp.indexOfFirst { it >= M }
    println(result)
}
