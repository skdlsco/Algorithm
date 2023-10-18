package `17404`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val N = reader.readLine().toInt()
    // r에서 시작한 rgb
    // g에서 시작한 rgb
    // b에서 시작한 rgb
    val dp = Array<IntArray>(2) { IntArray(9) { 1000000000 } }
    for (i in 1..N) {
        val cur = i % 2
        val prev = (i + 1) % 2
        val rgb = reader.readLine().split(" ").map { it.toInt() }
        if (i == 1) {
            dp[cur][0] = rgb[0]
            dp[cur][4] = rgb[1]
            dp[cur][8] = rgb[2]
        } else {
            for (j in 0 until 3) {
                val base = j * 3
                dp[cur][base] = min(dp[prev][base + 1], dp[prev][base + 2]) + rgb[0]
                dp[cur][base + 1] = min(dp[prev][base], dp[prev][base + 2]) + rgb[1]
                dp[cur][base + 2] = min(dp[prev][base], dp[prev][base + 1]) + rgb[2]
            }
        }
        if (i == N) {
            println(dp[cur].withIndex().minOf { if (it.index / 3 == it.index % 3) Int.MAX_VALUE else it.value})
        }
    }
}