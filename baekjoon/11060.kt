package `11060`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Int>(N) { 123456789 }
    dp[0] = 0
    for (i in 0 until N) {
        for (j in i..min(i + arr[i], N - 1)) {
            dp[j] = min(dp[j], dp[i] + 1)
        }
    }
    if (dp[N - 1] == 123456789)
        writer.write("-1")
    else
        writer.write("${dp[N - 1]}")
    writer.flush()
}