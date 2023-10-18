package `1932`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = Array<Array<Int>>(N) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }
    val dp = Array<Array<Int>>(N) { Array(it + 1) { 0 } }
    dp[0][0] = arr[0][0]
    (1 until N).forEach { y ->
        (0..y).forEach { x ->
            if (x > 0)
                dp[y][x] = dp[y - 1][x - 1]
            if (x < y)
                dp[y][x] = max(dp[y][x], dp[y - 1][x])
            dp[y][x] += arr[y][x]
        }
    }
    println(dp[N - 1].maxOf { it })
}