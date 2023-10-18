package `11055`

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
    val dp = Array<Int>(N) { 0 }
    for (i in 0 until N) {
        dp[i] = arr[i]
        for (j in 0 until i) {
            if (arr[j] < arr[i])
                dp[i] = max(dp[i], arr[i] + dp[j])
        }
    }
    writer.write("${dp.maxOf { it }}\n")
    writer.flush()
}