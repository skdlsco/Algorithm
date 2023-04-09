package `13398`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Array<Int>>(N) { Array(2) { 0 } }

    dp[0][0] = arr[0]
    dp[0][1] = arr[0]
    for (i in 1 until N) {
        dp[i][0] = max(dp[i - 1][0] + arr[i], arr[i])
        dp[i][1] = max(dp[i - 1][1] + arr[i], dp[i][0])
        if (i > 1)
            dp[i][1] = max(dp[i - 2][0] + arr[i], dp[i][1])
    }
    writer.write("${dp.maxOf { it.maxOf { it } }}\n")
    writer.flush()
}